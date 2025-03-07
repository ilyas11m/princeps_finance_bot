package com.project.princeps.princeps_finance_bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PrincepsFinanceBotApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(PrincepsFinanceBotApplication.class, args);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
