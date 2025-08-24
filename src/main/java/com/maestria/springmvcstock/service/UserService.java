package com.maestria.springmvcstock.service;

import java.util.Optional;

import com.maestria.springmvcstock.application.dto.LoginResponse;
import com.maestria.springmvcstock.model.User;

public interface UserService {
    Optional<String> authenticate(String username, String rawPassword);
    LoginResponse renewToken(String token);
    User save(User user);
}