package com.project.princeps.princeps_finance_bot.repository;

import com.project.princeps.princeps_finance_bot.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}