package com.thembelanimkhize.Mail_send2.service.impl;

import com.thembelanimkhize.Mail_send2.domain.Confirmation;
import com.thembelanimkhize.Mail_send2.domain.User;
import com.thembelanimkhize.Mail_send2.repository.ConfirmationRepository;
import com.thembelanimkhize.Mail_send2.repository.UserRepository;
import com.thembelanimkhize.Mail_send2.service.EmailService;
import com.thembelanimkhize.Mail_send2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final EmailService emailService;

    @Override
    public User saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setEnabled(false);
        userRepository.save(user);

//        Confirmation confirmation = new Confirmation(user);
//        confirmationRepository.save(confirmation);

        /* TODO Send email to user with token */
        emailService.sendHtmlEmail(user.getName(), user.getEmail() /**, confirmation.getToken()*/);

        return user;
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        user.setEnabled(true);
        userRepository.save(user);
        //confirmationRepository.delete(confirmation);
        return Boolean.TRUE;
    }
}