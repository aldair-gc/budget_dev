package com.aldairgc.budget_dev.service;

import com.aldairgc.budget_dev.domain.model.Transaction;

import java.util.List;

public interface TransactionService extends CrudService<Long, Transaction> {
    List<Transaction> findAllByUserId(Long userId);
}
