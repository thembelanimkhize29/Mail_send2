package com.thembelanimkhize.Mail_send2.service;

import com.thembelanimkhize.Mail_send2.domain.User;

public interface UserService {
    User saveUser(User user);
    Boolean verifyToken(String token);
}
