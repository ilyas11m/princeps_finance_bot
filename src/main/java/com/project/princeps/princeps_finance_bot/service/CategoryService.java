package com.project.princeps.princeps_finance_bot.service;

import com.project.princeps.princeps_finance_bot.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.jvnet.hk2.annotations.Service;

@Slf4j
@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

}
