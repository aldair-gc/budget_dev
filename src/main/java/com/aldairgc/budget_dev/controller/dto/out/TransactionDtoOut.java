package com.aldairgc.budget_dev.controller.dto.out;

import com.aldairgc.budget_dev.domain.model.Transaction;

import java.time.LocalDateTime;

public record TransactionDtoOut(
        Long id,
        String description,
        Double amount,
        LocalDateTime dueDate,
        LocalDateTime expiration,
        String type,
        String status,
        String frequency
) {

    public static TransactionDtoOut fromDomain(Transaction domain) {
        return new TransactionDtoOut(
                domain.getId(),
                domain.getDescription(),
                domain.getAmount(),
                domain.getDueDate(),
                domain.getExpiration(),
                domain.getType().name(),
                domain.getStatus().name(),
                domain.getFrequency().name()
        );
    }

}

