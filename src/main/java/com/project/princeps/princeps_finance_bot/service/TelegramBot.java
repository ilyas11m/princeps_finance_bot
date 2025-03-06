package com.project.princeps.princeps_finance_bot.service;

import com.project.princeps.princeps_finance_bot.components.Buttons;
import com.project.princeps.princeps_finance_bot.config.BotConfig;
import com.project.princeps.princeps_finance_bot.utils.BotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelegramBot extends TelegramLongPollingBot {

    BotConfig config;

    Buttons buttons;

    public static final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

    public TelegramBot(BotConfig config) {
        this.config = config;

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        List<InlineKeyboardButton> thirdRow = new ArrayList<>();

        InlineKeyboardButton helpButton = new InlineKeyboardButton(BotUtils.HELP_EMOJI + "\tПомощь");
        helpButton.setCallbackData("HELP_BUTTON");
        InlineKeyboardButton balanceButton = new InlineKeyboardButton(BotUtils.BALANCE + "\tБаланс");
        balanceButton.setCallbackData("BALANCE_BUTTON");
        InlineKeyboardButton incomeButton = new InlineKeyboardButton(BotUtils.INCOME + "\tДоход");
        incomeButton.setCallbackData("INCOME_BUTTON");
        InlineKeyboardButton expenseButton = new InlineKeyboardButton(BotUtils.EXPENSE + "\tРасход");
        expenseButton.setCallbackData("EXPENSE_BUTTON");
        InlineKeyboardButton budgetButton = new InlineKeyboardButton(BotUtils.BUDGET + "\tБюджет");
        budgetButton.setCallbackData("BUDGET_BUTTON");
        InlineKeyboardButton reportButton = new InlineKeyboardButton(BotUtils.REPORT + "\tОтчёт");
        reportButton.setCallbackData("REPORT_BUTTON");
        InlineKeyboardButton notificationButton = new InlineKeyboardButton(BotUtils.BELL_EMOJI + "\tУведомления");
        notificationButton.setCallbackData("NOTIFICATION_BUTTON");
        InlineKeyboardButton settingsButton = new InlineKeyboardButton(BotUtils.SETTINGS + "\tНастройки");
        settingsButton.setCallbackData("SETTINGS_BUTTON");

        firstRow.add(helpButton);
        firstRow.add(budgetButton);

        secondRow.add(incomeButton);
        secondRow.add(expenseButton);
        secondRow.add(budgetButton);

        thirdRow.add(reportButton);
        thirdRow.add(notificationButton);
        thirdRow.add(settingsButton);

        rows.add(firstRow);
        rows.add(secondRow);
        rows.add(thirdRow);

        markup.setKeyboard(rows);

        List<BotCommand> botCommandList = new ArrayList<>();
        botCommandList.add(new BotCommand("/start", "get a start message"));
        try {
            execute(new SetMyCommands(botCommandList, new BotCommandScopeDefault(), null));
        }
        catch (Exception e) {}
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
                case "/start":
                    sendMessage(chatId, BotUtils.GREETING, "HTML");
                    break;
                case "Помощь":
                    sendMessage(chatId, BotUtils.HELP_TEXT, "HTML");
                    break;
            }
        }
        else if (update.hasCallbackQuery()) {
            String callData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            switch (callData) {
                case "HELP_BUTTON":
                    sendMessage(chatId, BotUtils.HELP_TEXT, "HTML");
                    break;
            }
        }
    }

    public void sendMessage(long chatId, String text, String parseMode) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        message.enableHtml(true);
        message.setParseMode(parseMode);
        message.setReplyMarkup(markup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.error(e.getMessage());
        }
    }
}