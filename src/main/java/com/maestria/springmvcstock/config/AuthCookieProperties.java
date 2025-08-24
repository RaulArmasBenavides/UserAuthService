package com.maestria.springmvcstock.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "auth.cookie")
public class AuthCookieProperties {
    private String name = "ACCESS_TOKEN";
    private boolean secure = true; // en local sin HTTPS pon false por perfil
    private String sameSite = "Strict"; // Strict por tu requerimiento
    private String domain; // opcional (ej: midominio.com)
    private String path = "/";
    private long maxAgeSeconds = 864000; // 10 días

    // getters/setters
}