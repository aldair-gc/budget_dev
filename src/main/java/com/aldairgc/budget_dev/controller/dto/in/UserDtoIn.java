package com.aldairgc.budget_dev.controller.dto.in;

import com.aldairgc.budget_dev.domain.model.User;

public record UserDtoIn(
        String name,
        String email,
        String password
) {

    public static User toDomain(UserDtoIn dto) {
        var user = new User();
        user.setName(dto.name);
        user.setEmail(dto.email);
        user.setPassword(dto.password);
        return user;
    }

}
