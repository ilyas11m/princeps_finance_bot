package com.project.princeps.princeps_finance_bot.service;

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
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelegramBot extends TelegramLongPollingBot {

    BotConfig config;

    public static final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    public TelegramBot(BotConfig config) {
        this.config = config;
        List<BotCommand> botCommandList = new ArrayList<>();
        botCommandList.add(new BotCommand("/start", "get a start message"));
        botCommandList.add(new BotCommand("/help", "get a help info"));

        try {
            this.execute(new SetMyCommands(botCommandList, new BotCommandScopeDefault(), null));

        } catch (TelegramApiException e) {
            logger.error(e.getMessage());
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
                case "/start":
                    sendMessage(chatId, BotUtils.GREETING, "HTML");
                    break;
                case "/help":
                    sendMessage(chatId, BotUtils.HELP, "HTML");
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

        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.error(e.getMessage());
        }
    }

}
