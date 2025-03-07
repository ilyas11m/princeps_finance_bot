package com.project.princeps.princeps_finance_bot.components;

import com.project.princeps.princeps_finance_bot.utils.BotUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class Buttons {

    InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

    public InlineKeyboardMarkup setMarkup() {
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
        secondRow.add(balanceButton);

        thirdRow.add(reportButton);
        thirdRow.add(notificationButton);
        thirdRow.add(settingsButton);

        rows.add(firstRow);
        rows.add(secondRow);
        rows.add(thirdRow);

        markup.setKeyboard(rows);
        return markup;
    }
}
