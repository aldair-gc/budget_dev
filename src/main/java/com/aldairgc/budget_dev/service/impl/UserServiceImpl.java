package com.aldairgc.budget_dev.service.impl;

import com.aldairgc.budget_dev.domain.model.User;
import com.aldairgc.budget_dev.domain.repository.UserRepository;
import com.aldairgc.budget_dev.service.UserService;
import com.aldairgc.budget_dev.service.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Optional.ofNullable;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User create(User userDto) {
        ofNullable(userDto).orElseThrow(() -> new BusinessException("User cannot be null"));
        ofNullable(userDto.getName()).orElseThrow(() -> new BusinessException("Name cannot be null"));
        ofNullable(userDto.getEmail()).orElseThrow(() -> new BusinessException("Email cannot be null"));

        return userRepository.save(userDto);
    }

    @Transactional
    public User update(Long id, User userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new BusinessException("User not found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
