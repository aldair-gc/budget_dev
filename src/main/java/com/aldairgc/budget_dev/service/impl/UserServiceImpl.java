package com.aldairgc.budget_dev.service.impl;

import com.aldairgc.budget_dev.domain.model.User;
import com.aldairgc.budget_dev.domain.repository.UserRepository;
import com.aldairgc.budget_dev.service.UserService;
import com.aldairgc.budget_dev.service.exception.BusinessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User create(User dto) {
        ofNullable(dto).orElseThrow(() -> new BusinessException("User cannot be null"));
        ofNullable(dto.getName()).orElseThrow(() -> new BusinessException("Name cannot be null"));
        ofNullable(dto.getEmail()).orElseThrow(() -> new BusinessException("Email cannot be null"));
        ofNullable(dto.getPassword()).orElseThrow(() -> new BusinessException("Password cannot be null"));

        dto.setPasswordHash(passwordEncoder.encode(dto.getPassword()));

        return userRepository.save(dto);
    }

    @Transactional
    public User update(Long id, User dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new BusinessException("User not found"));
        ofNullable(dto.getName()).ifPresent(user::setName);
        ofNullable(dto.getEmail()).ifPresent(user::setEmail);
        ofNullable(dto.getPassword()).ifPresent(password -> user.setPasswordHash(passwordEncoder.encode(password)));
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
