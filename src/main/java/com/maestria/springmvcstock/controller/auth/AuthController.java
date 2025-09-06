package com.maestria.springmvcstock.controller.auth;

import com.maestria.springmvcstock.application.dto.LoginRequest;
import com.maestria.springmvcstock.application.dto.LoginResponse;
import com.maestria.springmvcstock.config.AuthCookieProperties;
import com.maestria.springmvcstock.model.User;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.ErrorResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.maestria.springmvcstock.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class AuthController {
    @Autowired
    private UserService userService;
    private final AuthCookieProperties cookieProps;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest req) {
        return userService.authenticate(req.username(), req.password())
                .map(jwt -> ResponseEntity.noContent()
                        .header(HttpHeaders.SET_COOKIE, buildAuthCookie(jwt).toString())
                        .build())
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/renewToken")
    public ResponseEntity<Void> renewToken(
            @CookieValue(name = "ACCESS_TOKEN", required = false) String token) {
        if (token == null)
            return ResponseEntity.status(401).build();
        var renewed = userService.renewToken(token);
        if (renewed == null)
            return ResponseEntity.status(401).build();

        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, buildAuthCookie(renewed.getToken()).toString())
                .build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, buildDeleteCookie().toString())
                .build();
    }

    private ResponseCookie buildAuthCookie(String jwt) {
        ResponseCookie.ResponseCookieBuilder b = ResponseCookie.from(cookieProps.getName(), jwt)
                .httpOnly(true)
                .secure(cookieProps.isSecure())
                .sameSite(cookieProps.getSameSite())
                .path(cookieProps.getPath())
                .maxAge(Duration.ofSeconds(cookieProps.getMaxAgeSeconds()));
        if (cookieProps.getDomain() != null && !cookieProps.getDomain().isBlank()) {
            b.domain(cookieProps.getDomain());
        }
        return b.build();
    }

    private ResponseCookie buildDeleteCookie() {
        ResponseCookie.ResponseCookieBuilder b = ResponseCookie.from(cookieProps.getName(), "")
                .httpOnly(true)
                .secure(cookieProps.isSecure())
                .sameSite(cookieProps.getSameSite())
                .path(cookieProps.getPath())
                .maxAge(Duration.ZERO);
        if (cookieProps.getDomain() != null && !cookieProps.getDomain().isBlank()) {
            b.domain(cookieProps.getDomain());
        }
        return b.build();
    }

}
