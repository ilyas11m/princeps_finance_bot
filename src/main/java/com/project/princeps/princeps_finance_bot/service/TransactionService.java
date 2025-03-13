package com.project.princeps.princeps_finance_bot.service;

import com.project.princeps.princeps_finance_bot.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.jvnet.hk2.annotations.Service;

@Slf4j
@Service
public class TransactionService {
    TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository)  {
        this.transactionRepository = transactionRepository;
    }



}
