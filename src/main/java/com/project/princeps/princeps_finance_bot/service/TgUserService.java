package com.project.princeps.princeps_finance_bot.service;

import com.project.princeps.princeps_finance_bot.model.TgUser;
import com.project.princeps.princeps_finance_bot.repository.TgUserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TgUserService {

    TgUserRepository tgUserRepository;

    public TgUserService(TgUserRepository tgUserRepository) {
        this.tgUserRepository = tgUserRepository;
    }


    @Transactional
    public void addTgUser(TgUser tgUser) {
        try {
            tgUserRepository.save(tgUser);
        } catch (Exception e) {
            throw new RuntimeException("User exists!");
        }

    }
}
