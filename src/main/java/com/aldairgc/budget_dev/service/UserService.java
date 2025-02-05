package com.aldairgc.budget_dev.service;

import com.aldairgc.budget_dev.domain.model.User;

public interface UserService {
    User create(User userDto);

    User update(Long id, User userDto);

    void delete(Long id);
}
