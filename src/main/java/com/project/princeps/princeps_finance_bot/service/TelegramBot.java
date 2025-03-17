package com.project.princeps.princeps_finance_bot.service;

import com.project.princeps.princeps_finance_bot.components.Buttons;
import com.project.princeps.princeps_finance_bot.config.BotConfig;
import com.project.princeps.princeps_finance_bot.model.Budget;
import com.project.princeps.princeps_finance_bot.model.TgUser;
import com.project.princeps.princeps_finance_bot.utils.BotUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class TelegramBot extends TelegramLongPollingBot {

    private final BudgetService budgetService;

    private final TgUserService tgUserService;

    private final BotConfig config;

    private final Buttons buttons;

    private HashMap<Long, String> dataState = new HashMap<>();

    @Autowired
    public TelegramBot(BotConfig config, Buttons buttons,
                       TgUserService tgUserService, BudgetService budgetService) {
        this.config = config;
        this.buttons = buttons;
        this.tgUserService = tgUserService;
        this.budgetService = budgetService;

        List<BotCommand> botCommandList = new ArrayList<>();
        botCommandList.add(new BotCommand("/start", "get a start message"));
        botCommandList.add(new BotCommand("/register", "register your profile"));
        botCommandList.add(new BotCommand("/budget", "set your budget"));

        try {
            execute(new SetMyCommands(botCommandList, new BotCommandScopeDefault(), null));
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return config.botName;
    }

    @Override
    public String getBotToken() {
        return config.token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String command = update.getMessage().getText();
            String balanceButton = "Баланс";
            Long tgUserId = update.getMessage().getChat().getId();
            if (command.contains(balanceButton)) {
                dataState.put(chatId, "balance_response");
                sendMessage(chatId, BotUtils.BUDGET_UPDATE, "HTML");
                sendMessage(chatId, "Введите баланс:", null);
                return;
            }
            switch (command) {
                case "/start":
                    sendMessage(chatId, BotUtils.GREETING, "HTML", buttons.inlineMarkup());
                    break;
                case "/register":
                    LocalDateTime current = LocalDateTime.now();
                    Timestamp time = Timestamp.valueOf(current);
                    String username = update.getMessage().getChat().getUserName();
                    tgUserRegistration(update,time,tgUserId,username);
                    break;
                default:
                    if (dataState.containsKey(chatId) && dataState.get(chatId).equals("balance_response")) {
                        try {
                            long balance = Long.valueOf(update.getMessage().getText());
                            long limit = 123;
                            Timestamp timestamp = Timestamp.from(Instant.now());
                            budgetCreation(update, tgUserId, balance, limit, timestamp);
                            dataState.remove(chatId);
                        } catch (Exception e) {
                            sendMessage(chatId, "Некорректный ввод!", "HTML");
                            log.error(e.getMessage());
                        }
                    }
                    break;
            }
        }

        else if (update.hasCallbackQuery()) {
            String callData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (callData.equals("HELP_BUTTON")) {
                sendMessage(chatId, BotUtils.HELP_TEXT, "HTML");
            }
        }
    }

    public void budgetCreation(Update update, long tgUserId, long balance, long limit,
                               Timestamp periodBegin) {
        long chatId = update.getMessage().getChatId();
        Budget budget = new Budget();
        budget.setUserId(tgUserService.findById(tgUserId));
        budget.setBalance(balance);
        budget.setLimitAmount(limit);
        budget.setPeriodBegin(periodBegin);
        try {
            budgetService.add(budget);
            sendMessage(chatId, "Бюджет обновлен!", "HTML");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void tgUserRegistration(Update update, Timestamp time, Long tgUserId, String username) {
        long chatId = update.getMessage().getChatId();
        TgUser tgUser = new TgUser();
        tgUser.setTgUserId(tgUserId);
        tgUser.setUsername(username);
        tgUser.setTimeRegistration(time);
        try {
            tgUserService.addTgUser(tgUser);
            log.info("SUCCESS, TG USER ADDED");
            sendMessage(chatId, "Вы успешно <u>зарегистрировались</u>!\t" + BotUtils.MARK, "HTML");
        } catch (Exception e) {
            sendMessage(chatId, "Вы уже <u>зарегистрированы</u>!\t" + BotUtils.X, "HTML");
            log.error(e.getMessage());
        }
    }

    public void sendMessage(long chatId, String text, String parseMode, InlineKeyboardMarkup markup) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        message.enableHtml(true);
        message.setParseMode(parseMode);
        message.setReplyMarkup(markup);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }


    public void sendMessage(long chatId, String text, String parseMode) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        message.enableHtml(true);
        message.setParseMode(parseMode);
        message.setReplyMarkup(buttons.replyMarkup());

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.info(e.getMessage());
        }
    }
}