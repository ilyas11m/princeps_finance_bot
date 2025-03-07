package com.project.princeps.princeps_finance_bot.repository;

import com.project.princeps.princeps_finance_bot.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}