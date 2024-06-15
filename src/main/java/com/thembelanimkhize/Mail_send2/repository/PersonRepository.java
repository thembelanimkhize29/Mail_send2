package com.thembelanimkhize.Mail_send2.repository;

import com.thembelanimkhize.Mail_send2.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

}
