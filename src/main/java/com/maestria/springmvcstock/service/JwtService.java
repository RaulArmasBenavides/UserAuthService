package com.maestria.springmvcstock.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String generateToken(String username);
    Claims parseClaims(String token);
    boolean isExpired(String token);
}