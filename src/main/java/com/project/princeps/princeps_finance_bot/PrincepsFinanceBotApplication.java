package com.project.princeps.princeps_finance_bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrincepsFinanceBotApplication {
    public static final Logger logger = LoggerFactory.getLogger(PrincepsFinanceBotApplication.class);
    public static void main(String[] args) {
        try {
            SpringApplication.run(PrincepsFinanceBotApplication.class, args);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
