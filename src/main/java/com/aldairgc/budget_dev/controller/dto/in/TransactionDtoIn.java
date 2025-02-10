package com.aldairgc.budget_dev.controller.dto.in;

import com.aldairgc.budget_dev.domain.model.FrequencyEnum;
import com.aldairgc.budget_dev.domain.model.StatusEnum;
import com.aldairgc.budget_dev.domain.model.Transaction;
import com.aldairgc.budget_dev.domain.model.TransactionEnum;

import java.time.LocalDateTime;

public record TransactionDtoIn(
        Long id,
        String description,
        Double amount,
        LocalDateTime dueDate,
        LocalDateTime expiration,
        String type,
        String status,
        String frequency,
        Integer quantity
) {

    public static Transaction toDomain(TransactionDtoIn dto) {
        var transaction = new Transaction();
        transaction.setId(dto.id);
        transaction.setDescription(dto.description);
        transaction.setAmount(dto.amount);
        transaction.setDueDate(dto.dueDate);
        transaction.setExpiration(dto.expiration);
        transaction.setType(TransactionEnum.valueOf(dto.type));
        transaction.setStatus(StatusEnum.valueOf(dto.status));
        transaction.setFrequency(FrequencyEnum.valueOf(dto.frequency));
        transaction.setQuantity(dto.quantity);
        return transaction;
    }

}

