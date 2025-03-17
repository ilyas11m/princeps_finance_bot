package com.project.princeps.princeps_finance_bot.utils;

import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Component;

@Component
public class BotUtils {
    /** Emojis **/
    public static final String BELL_EMOJI = EmojiParser.parseToUnicode(":bell:");
    public static final String EXPENSE = EmojiParser.parseToUnicode(":chart_with_downwards_trend:");
    public static final String INCOME = EmojiParser.parseToUnicode(":chart_with_upwards_trend:");
    public static final String BUDGET = EmojiParser.parseToUnicode(":money_with_wings:");
    public static final String HELP_EMOJI = EmojiParser.parseToUnicode(":book:");
    public static final String BALANCE = EmojiParser.parseToUnicode(":bank:");
    public static final String REPORT = EmojiParser.parseToUnicode(":bar_chart:");
    public static final String SETTINGS = EmojiParser.parseToUnicode(":gear:");
    public static final String HAMMER = EmojiParser.parseToUnicode(":hammer_and_wrench:");
    public static final String WINK = EmojiParser.parseToUnicode(":wink:");
    public static final String MARK = EmojiParser.parseToUnicode(":white_check_mark:");
    public static final String X = EmojiParser.parseToUnicode(":x:");

    public static final String HELP_TEXT = "\uD83D\uDCA1 Команды бота для управления вашими финансами:\n" +
            "\n"+
            "<em><b>Помощь</b></em> — Получить информацию о доступных командах бота (вы сейчас используете эту команду \uD83D\uDE0A).\n" +
            "\n" +
            "<em><b>Баланс</b></em> — Посмотреть текущий баланс вашего счета.\n" +
            "\n" +
            "<em><b>Доход</b></em> — Добавить новый доход. Вы можете указать сумму и источник дохода (например, зарплата, фриланс и т. д.).\n" +
            "\n" +
            "<em><b>Расход</b></em> — Добавить новый расход. Укажите сумму и категорию расхода (например, еда, транспорт, развлечения и т. д.).\n" +
            "\n" +
            "<em><b>Бюджет</b></em> — Установить лимит по расходам на месяц или по категории (например, лимит на еду или развлечение).\n" +
            "\n" +
            "<em><b>Отчет</b></em> — Получить отчет о расходах за выбранный период. Это поможет вам отслеживать свои траты и планировать бюджет.\n" +
            "\n" +
            "<em><b>Уведомления</b></em> — Настроить уведомления о превышении бюджета по категориям.\n" +
            "\n" +
            "<em><b>Настройки</b></em> — Изменить настройки вашего аккаунта, такие как валюта и язык.\n" +
            "\n";

    public static final String GREETING = "<em><b>Привет</b></em>! Я — твой <u>финансовый помощник</u>. 💰\n" +
            "Я помогу тебе отслеживать расходы, доходы и планировать бюджет.\n" +
            "Для начала нажми кнопку \"Помощь\", чтобы узнать, как я могу тебе помочь!";

    public static final String MENU_TEXT = "<em><b>Вот все доступные функции бота!</b></em>: " + HAMMER;

    public static final String BUDGET_UPDATE = "<em><b>Установите бюджет бота :)</b></em>";

}
