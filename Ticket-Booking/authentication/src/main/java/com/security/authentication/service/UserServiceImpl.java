package com.security.authentication.service;

import com.security.authentication.model.User;
import com.security.authentication.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo repo;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired private JwtService jwtService;

    @Override
    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public String generate(String username) {
        return jwtService.generateToken(username);
    }

    @Override
    public void validate(String token) {
        jwtService.validateToken(token);
    }
}
