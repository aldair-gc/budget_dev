package com.aldairgc.budget_dev.service;

import com.aldairgc.budget_dev.domain.model.User;

import java.util.List;

public interface UserService extends CrudService<Long, User> {
    List<User> findAll();
}
