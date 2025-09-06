package com.maestria.springmvcstock.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maestria.springmvcstock.application.dto.LoginLegacyResponse;
import com.maestria.springmvcstock.application.dto.LoginRequest;
import com.maestria.springmvcstock.application.dto.LoginResponse;
import com.maestria.springmvcstock.service.JwtService;
import com.maestria.springmvcstock.service.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LegacyAuthController {

    private final UserService userService;
    private final JwtService jwt;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        return userService.authenticate(req.username(), req.password())
                .map(token -> ResponseEntity.ok(new LoginLegacyResponse(token, jwt.getExpirationSeconds())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    // @GetMapping("/me")
    // public ResponseEntity<?> me(@RequestHeader(name = "Authorization", required = false) String auth) {
    //     if (auth == null || !auth.startsWith("Bearer ")) return ResponseEntity.status(401).build();
    //     var token = auth.substring("Bearer ".length());
    //     if (!jwt.isValid(token)) return ResponseEntity.status(401).build();
    //     var subject = jwt.parse(token).getBody().getSubject();
    //     return ResponseEntity.ok(java.util.Map.of("user", subject));
    // }
}