package com.project.princeps.princeps_finance_bot.components;

import com.project.princeps.princeps_finance_bot.utils.BotUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class Buttons {

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    InlineKeyboardMarkup inlineMarkup = new InlineKeyboardMarkup();

    public InlineKeyboardMarkup inlineMarkup() {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        InlineKeyboardButton helpButton = new InlineKeyboardButton(BotUtils.HELP_EMOJI + "\tПомощь");
        helpButton.setCallbackData("HELP_BUTTON");
        firstRow.add(helpButton);
        rows.add(firstRow);
        inlineMarkup.setKeyboard(rows);
        return inlineMarkup;
    }

    @Bean
    public ReplyKeyboardMarkup replyMarkup() {

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardButton balanceButton = new KeyboardButton(BotUtils.BALANCE + "\tБаланс");
        KeyboardButton incomeButton = new KeyboardButton(BotUtils.INCOME + "\tДоход");
        KeyboardButton expenseButton = new KeyboardButton(BotUtils.EXPENSE + "\tРасход");
        KeyboardButton budgetButton = new KeyboardButton(BotUtils.BUDGET + "\tБюджет");
        KeyboardButton reportButton = new KeyboardButton(BotUtils.REPORT + "\tОтчёт");
        KeyboardButton notificationButton = new KeyboardButton(BotUtils.BELL_EMOJI + "\tУведомления");
        KeyboardButton settingsButton = new KeyboardButton(BotUtils.SETTINGS + "\tНастройки");

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(balanceButton);

        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(incomeButton);
        secondRow.add(expenseButton);
        secondRow.add(budgetButton);

        KeyboardRow thirdRow = new KeyboardRow();
        thirdRow.add(reportButton);
        thirdRow.add(notificationButton);
        thirdRow.add(settingsButton);
        rows.add(firstRow);
        rows.add(secondRow);
        rows.add(thirdRow);

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(rows);

        return replyKeyboardMarkup;
    }
}
