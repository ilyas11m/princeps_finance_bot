package com.project.princeps.princeps_finance_bot.service;

import com.project.princeps.princeps_finance_bot.model.Budget;
import com.project.princeps.princeps_finance_bot.repository.BudgetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BudgetService {

    BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public void add(Budget budget) {
        try {
            budgetRepository.save(budget);
            log.info("SUCCESS, BUDGET UPDATED");
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
