package com.maestria.springmvcstock.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maestria.springmvcstock.application.dto.LoginResponse;
import com.maestria.springmvcstock.model.User;
import com.maestria.springmvcstock.repository.UserRepository;
import com.maestria.springmvcstock.service.JwtService;
import com.maestria.springmvcstock.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<String> authenticate(String username, String rawPassword) {
        return userRepository.findByUsername(username)
                .filter(u -> passwordEncoder.matches(rawPassword, u.getPassword()))
                .map(u -> jwtService.generateToken(u.getUsername()));
    }

    @Override
    public LoginResponse renewToken(String token) {
        var claims = jwtService.parseClaims(token);
        var username = claims.getSubject();
        return userRepository.findByUsername(username)
                .map(u -> new LoginResponse(username, jwtService.generateToken(username)))
                .orElse(null);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
