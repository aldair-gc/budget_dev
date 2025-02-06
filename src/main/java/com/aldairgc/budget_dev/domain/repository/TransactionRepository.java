package com.aldairgc.budget_dev.domain.repository;

import com.aldairgc.budget_dev.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
