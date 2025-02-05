package com.aldairgc.budget_dev.domain.repository;

import com.aldairgc.budget_dev.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
