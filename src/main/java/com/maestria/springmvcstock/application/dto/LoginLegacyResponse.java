package com.maestria.springmvcstock.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class LoginLegacyResponse {
    private String token;
    private long expiresInSeconds;
}