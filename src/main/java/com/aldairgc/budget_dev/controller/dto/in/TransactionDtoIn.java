package com.aldairgc.budget_dev.controller.dto.in;

import com.aldairgc.budget_dev.domain.model.*;

import java.sql.Date;

public record TransactionDtoIn(
        Long id,
        Long userId,
        String description,
        Double amount,
        Date dueDate,
        Date expiration,
        String type,
        String status,
        String frequency,
        Integer quantity
) {

    public static Transaction toDomain(TransactionDtoIn dto) {
        var transaction = new Transaction();
        transaction.setId(dto.id);
        transaction.setUser(new User());
        transaction.getUser().setId(dto.userId);
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

