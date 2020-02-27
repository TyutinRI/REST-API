package com.tyutin.repository;

import com.tyutin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository интерфейс для {@link User}
 */

public interface UserRepository extends JpaRepository<User, Long> {
}
