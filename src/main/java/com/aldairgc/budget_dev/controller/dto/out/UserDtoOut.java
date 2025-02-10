package com.aldairgc.budget_dev.controller.dto.out;

import com.aldairgc.budget_dev.domain.model.User;

public record UserDtoOut(
        Long id,
        String name,
        String email
) {

    public static UserDtoOut fromDomain(User domain) {
        return new UserDtoOut(
                domain.getId(),
                domain.getName(),
                domain.getEmail()
        );
    }

}
