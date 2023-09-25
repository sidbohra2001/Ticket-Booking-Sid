package com.security.authentication.service;

import com.security.authentication.model.User;

public interface UserService {
    User register(User user);
    String generate(String username);
    void validate(String token);
}
