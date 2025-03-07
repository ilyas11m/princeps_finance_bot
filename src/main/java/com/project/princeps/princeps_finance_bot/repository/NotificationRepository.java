package com.project.princeps.princeps_finance_bot.repository;

import com.project.princeps.princeps_finance_bot.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}