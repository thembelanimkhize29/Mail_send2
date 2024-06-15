package com.thembelanimkhize.Mail_send2.service;

import com.thembelanimkhize.Mail_send2.domain.Person;
import com.thembelanimkhize.Mail_send2.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }
}
