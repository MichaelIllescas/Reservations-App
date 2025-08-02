package com.reservastrenque.reservas_trenque.auth.service;

import com.reservastrenque.reservas_trenque.auth.dto.AuthenticationRequest;
import com.reservastrenque.reservas_trenque.auth.dto.AuthenticationResponse;
import com.reservastrenque.reservas_trenque.auth.exceptions.InvalidCredentialsException;
import com.reservastrenque.reservas_trenque.auth.security.JwtService;
import com.reservastrenque.reservas_trenque.auth.repository.PasswordResetTokenRepository;
import com.reservastrenque.reservas_trenque.users.domain.User;
import com.reservastrenque.reservas_trenque.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private PasswordResetTokenRepository tokenRepository;
    @Mock
    private EmailService emailService;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void loginReturnsTokenWhenCredentialsValid() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setEnabled(true);

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(jwtService.generateToken(any(HashMap.class), eq(user))).thenReturn("token123");

        AuthenticationRequest request = new AuthenticationRequest("test@example.com", "password");

        AuthenticationResponse response = authenticationService.login(request);

        assertEquals("token123", response.getJwt());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void loginThrowsExceptionWhenUserNotFound() {
        when(userRepository.findByEmail("missing@example.com")).thenReturn(Optional.empty());

        AuthenticationRequest request = new AuthenticationRequest("missing@example.com", "password");

        assertThrows(InvalidCredentialsException.class, () -> authenticationService.login(request));
    }
}
