package com.thembelanimkhize.Mail_send2.repository;

import com.thembelanimkhize.Mail_send2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmailIgnoreCase(String email);
    Boolean existsByEmail(String email);
}
