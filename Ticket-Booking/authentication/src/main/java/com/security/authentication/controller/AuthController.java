package com.security.authentication.controller;

import com.security.authentication.dto.RegisterResponseDto;
import com.security.authentication.dto.UserDto;
import com.security.authentication.model.User;
import com.security.authentication.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired private UserService service;
    @Autowired private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public RegisterResponseDto register(@RequestBody UserDto userDto){
        User user = User.builder().username(userDto.getUsername()).password(userDto.getPassword()).build();
        RegisterResponseDto registerResponseDto = new RegisterResponseDto();
        BeanUtils.copyProperties(service.register(user), registerResponseDto);
        return registerResponseDto;
    }

    @PostMapping("/generate")
    public String generate(@RequestBody UserDto user){
        Authentication authenticate =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(!authenticate.isAuthenticated()) throw new RuntimeException("Invalid Access !!! User "+user.getUsername()+" is not authenticated.");
        return service.generate(user.getUsername());
    }

    @GetMapping("/validate")
    public String validate(@RequestParam("token") String token){
        service.validate(token);
        return "Token is valid";
    }
}
