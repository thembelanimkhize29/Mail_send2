package com.thembelanimkhize.Mail_send2.controllers;

import com.thembelanimkhize.Mail_send2.domain.HttpResponse;
import com.thembelanimkhize.Mail_send2.domain.Person;
import com.thembelanimkhize.Mail_send2.domain.User;
import com.thembelanimkhize.Mail_send2.service.PersonService;
import com.thembelanimkhize.Mail_send2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<HttpResponse> createPerson(@RequestBody Person request) {
        Person person = new Person();
        person.setName(request.getName());
        person.setStatus(request.getStatus());

        // Save the Person entity
        Person newPerson = personService.savePerson(person);

        // Create and save the associated User entities
        for (User userRequest : request.getUsers()) {
            User newUser = new User();
            newUser.setName(userRequest.getName());
            newUser.setEmail(userRequest.getEmail());
            newUser.setEnabled(userRequest.isEnabled());
            newUser.setPerson(newPerson);
            userService.saveUser(newUser);
        }
        return ResponseEntity.created(URI.create(""))
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("person", newPerson))
                        .message("Person created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build());
    }

    @GetMapping
    public ResponseEntity<HttpResponse> confirmUserAccount(@RequestParam("token") String token) {
        Boolean isSuccess = userService.verifyToken(token);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Success", isSuccess))
                        .message("Account Verified")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
