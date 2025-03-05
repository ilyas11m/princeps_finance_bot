package com.project.princeps.princeps_finance_bot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@Configuration
@PropertySource("classpath:/application.properties")
public class BotConfig {

    @Value("${bot.name}")
    public String botName;

    @Value("${bot.token}")
    public String token;
}
