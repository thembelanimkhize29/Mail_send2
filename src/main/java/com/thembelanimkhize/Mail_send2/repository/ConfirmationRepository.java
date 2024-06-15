package com.thembelanimkhize.Mail_send2.repository;

import com.thembelanimkhize.Mail_send2.domain.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConfirmationRepository extends JpaRepository<Confirmation, UUID> {
    Confirmation findByToken(String token);
}
