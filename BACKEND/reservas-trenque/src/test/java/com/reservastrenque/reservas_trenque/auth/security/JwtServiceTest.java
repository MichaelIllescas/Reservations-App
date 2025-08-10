package com.reservastrenque.reservas_trenque.auth.security;

import com.reservastrenque.reservas_trenque.users.domain.Role;
import com.reservastrenque.reservas_trenque.users.domain.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    private JwtService jwtService;
    private static final String SECRET_KEY = "MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTIzNDU2Nzg5MDE=";


    private User userDetails;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secretKey", SECRET_KEY);

        // Instancia real de User: el getAuthorities() saldrá de role
        userDetails = User.builder()
                .id(1L)
                .email("user@example.com")  // getUsername() usa email
                .password("x")               // no importa el valor en estos tests
                .enabled(true)
                .role(Role.USER)             // <- CLAVE: esto hace que getAuthorities() devuelva ROLE_USER
                .build();
    }

    @Test
    void generateToken_ShouldIncludeUserIdAndRoleInClaims() {
        String token = jwtService.generateToken(new HashMap<>(), userDetails);

        Long userId = jwtService.extractClaim(token, claims -> claims.get("userId", Long.class));
        String role = jwtService.extractClaim(token, claims -> claims.get("role", String.class));

        assertEquals(1L, userId);
        assertEquals("ROLE_USER", role);
    }

    @Test
    void isTokenValid_ShouldReturnTrueForValidToken() {
        String token = jwtService.generateToken(new HashMap<>(), userDetails);

        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

    @Test
    void isTokenValid_ShouldThrowExceptionForExpiredToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", 1L);
        claims.put("role", "ROLE_USER");

        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

        String expiredToken = Jwts.builder()
                .setClaims(claims)
                .setSubject("user@example.com")
                .setIssuedAt(new Date(System.currentTimeMillis() - 3600000))
                .setExpiration(new Date(System.currentTimeMillis() - 1000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        assertThrows(ExpiredJwtException.class,
                () -> jwtService.isTokenValid(expiredToken, userDetails));
    }


    @Test
    void extractUserId_ShouldReturnCorrectId() {
        String token = jwtService.generateToken(new HashMap<>(), userDetails);

        assertEquals(1L, jwtService.extractUserId(token));
    }
}

