package com.aldairgc.budget_dev.service.impl;

import com.aldairgc.budget_dev.domain.model.Transaction;
import com.aldairgc.budget_dev.domain.repository.TransactionRepository;
import com.aldairgc.budget_dev.service.TransactionService;
import com.aldairgc.budget_dev.service.exception.BusinessException;
import com.aldairgc.budget_dev.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction create(Transaction dto) {
        ofNullable(dto).orElseThrow(() -> new BusinessException("Transaction cannot be null"));
        ofNullable(dto.getAmount()).orElseThrow(() -> new BusinessException("Amount cannot be null"));
        ofNullable(dto.getDescription()).orElseThrow(() -> new BusinessException("Description cannot be null"));
        ofNullable(dto.getType()).orElseThrow(() -> new BusinessException("Type cannot be null"));
        ofNullable(dto.getDueDate()).orElseThrow(() -> new BusinessException("Due date cannot be null"));

        return transactionRepository.save(dto);
    }

    @Transactional
    public Transaction update(Long id, Transaction dto) {
        Transaction transaction = transactionRepository
            .findById(id)
            .orElseThrow(NotFoundException::new);

        Optional.ofNullable(dto.getAmount()).ifPresent(transaction::setAmount);
        Optional.ofNullable(dto.getDescription()).ifPresent(transaction::setDescription);
        Optional.ofNullable(dto.getType()).ifPresent(transaction::setType);
        Optional.ofNullable(dto.getDueDate()).ifPresent(transaction::setDueDate);
        Optional.ofNullable(dto.getType()).ifPresent(transaction::setType);
        Optional.ofNullable(dto.getExpiration()).ifPresent(transaction::setExpiration);
        Optional.ofNullable(dto.getStatus()).ifPresent(transaction::setStatus);

        return transactionRepository.save(transaction);
    }

    @Transactional
    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElseThrow(NotFoundException::new);
    }

}
