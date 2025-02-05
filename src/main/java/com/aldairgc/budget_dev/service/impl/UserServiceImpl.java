package com.aldairgc.budget_dev.service.impl;

import com.aldairgc.budget_dev.domain.model.User;
import com.aldairgc.budget_dev.domain.repository.UserRepository;
import com.aldairgc.budget_dev.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User userDto) {
        return userRepository.save(userDto);
    }

    @Override
    public User update(Long id, User userDto) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {

    }
}
