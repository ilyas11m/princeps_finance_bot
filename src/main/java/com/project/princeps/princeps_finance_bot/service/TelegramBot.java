package com.project.princeps.princeps_finance_bot.service;

import com.project.princeps.princeps_finance_bot.components.Buttons;
import com.project.princeps.princeps_finance_bot.config.BotConfig;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TelegramBot extends TelegramLongPollingBot {

    private final TgUserService tgUserService;

    private final BotConfig config;

    private final Buttons buttons;;

    @Autowired
    public TelegramBot(BotConfig config, Buttons buttons,
                       TgUserService tgUserService) {
        this.config = config;
        this.buttons = buttons;
        this.tgUserService = tgUserService;

        List<BotCommand> botCommandList = new ArrayList<>();
        botCommandList.add(new BotCommand("/start", "get a start message"));
        botCommandList.add(new BotCommand("/menu", "get a bot's available functionality"));
        botCommandList.add(new BotCommand("/register", "register your profile"));
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
            switch (command) {

                case "/register":
                    LocalDateTime current = LocalDateTime.now();
                    Timestamp time = Timestamp.valueOf(current);
                    Long tgUserId = update.getMessage().getChat().getId();
                    String username = update.getMessage().getChat().getUserName();
                    tgUserRegistration(update,time,tgUserId,username);
                    break;
                case "/start":
                    sendMessage(chatId, BotUtils.GREETING, "HTML");
                    break;
                case "/menu":
                    sendMessage(chatId, BotUtils.MENU_TEXT, "HTML", buttons.setMarkup());
                    break;
            }
        }

        else if (update.hasCallbackQuery()) {
            String callData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            switch (callData) {
                case "HELP_BUTTON":
                    sendMessage(chatId, BotUtils.HELP_TEXT, "HTML", buttons.setMarkup());
                    break;
            }
        }
    }

    public void tgUserRegistration(Update update, Timestamp time, Long tgUserId, String username) {
        long chatId = update.getMessage().getChatId();
        TgUser tgUser = new TgUser();
        tgUser.setTg_user_id(tgUserId);
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

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.info(e.getMessage());
        }
    }
}