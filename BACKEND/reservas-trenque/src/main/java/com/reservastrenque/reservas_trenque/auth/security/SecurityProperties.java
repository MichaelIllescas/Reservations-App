package com.reservastrenque.reservas_trenque.auth.security;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
    private List<String> whitelist;
    private List<String> corsAllowedOrigins;
    private List<String> corsAllowedPaths;
}