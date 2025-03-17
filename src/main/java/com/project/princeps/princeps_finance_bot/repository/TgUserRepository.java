package com.project.princeps.princeps_finance_bot.repository;

import com.project.princeps.princeps_finance_bot.model.TgUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface TgUserRepository extends JpaRepository<TgUser, Long> {
    Optional<TgUser> findByTgUserId(long tgUserId);
}
