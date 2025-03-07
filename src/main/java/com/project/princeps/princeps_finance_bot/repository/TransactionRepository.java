package com.project.princeps.princeps_finance_bot.repository;

import com.project.princeps.princeps_finance_bot.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}