package com.project.princeps.princeps_finance_bot.states;

public class BudgetData {

    BotState botState;

    Long data;

    public BudgetData(BotState botState, Long data) {
        this.data = data;
        this.botState = botState;
    }

    public BotState getBotState() {
        return botState;
    }

    public void setBotState(BotState botState) {
        this.botState = botState;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
    }
}
