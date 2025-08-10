package com.reservastrenque.reservas_trenque.auth.service;

import com.reservastrenque.reservas_trenque.auth.dto.AuthenticationRequest;
import com.reservastrenque.reservas_trenque.auth.dto.AuthenticationResponse;
import com.reservastrenque.reservas_trenque.auth.entity.PasswordResetToken;
import com.reservastrenque.reservas_trenque.auth.exceptions.DisabledUserException;
import com.reservastrenque.reservas_trenque.auth.exceptions.InvalidCredentialsException;
import com.reservastrenque.reservas_trenque.auth.repository.PasswordResetTokenRepository;
import com.reservastrenque.reservas_trenque.auth.security.JwtService;
import com.reservastrenque.reservas_trenque.users.domain.User;
import com.reservastrenque.reservas_trenque.users.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
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

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(authenticationService, "resetPasswordBaseUrl", "http://frontend/reset");
    }

    @Test
    void login_ShouldReturnToken_WhenCredentialsAreValid() {
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email("user@example.com")
                .password("password")
                .build();
        User user = User.builder()
                .email("user@example.com")
                .enabled(true)
                .build();

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(jwtService.generateToken(anyMap(), eq(user))).thenReturn("jwt-token");

        AuthenticationResponse response = authenticationService.login(request);

        assertEquals("jwt-token", response.getJwt());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService).generateToken(anyMap(), eq(user));
    }

    @Test
    void login_ShouldThrowInvalidCredentials_WhenUserNotFound() {
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email("missing@example.com")
                .password("password")
                .build();

        when(userRepository.findByEmail("missing@example.com")).thenReturn(Optional.empty());

        assertThrows(InvalidCredentialsException.class, () -> authenticationService.login(request));
    }

    @Test
    void login_ShouldThrowInvalidCredentials_WhenPasswordIncorrect() {
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email("user@example.com")
                .password("wrong")
                .build();
        User user = User.builder()
                .email("user@example.com")
                .enabled(true)
                .build();

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        doThrow(new RuntimeException()).when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        assertThrows(InvalidCredentialsException.class, () -> authenticationService.login(request));
    }

    @Test
    void login_ShouldThrowDisabledUserException_WhenUserDisabled() {
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email("user@example.com")
                .password("password")
                .build();
        User user = User.builder()
                .email("user@example.com")
                .enabled(false)
                .build();

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));

        assertThrows(DisabledUserException.class, () -> authenticationService.login(request));
        verify(authenticationManager, never()).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void sendPasswordResetToken_ShouldCreateTokenAndSendEmail() {
        User user = User.builder()
                .email("user@example.com")
                .build();

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(tokenRepository.findByUser(user)).thenReturn(Optional.empty());

        authenticationService.sendPasswordResetToken("user@example.com");

        ArgumentCaptor<PasswordResetToken> tokenCaptor = ArgumentCaptor.forClass(PasswordResetToken.class);
        verify(tokenRepository).save(tokenCaptor.capture());
        PasswordResetToken savedToken = tokenCaptor.getValue();
        assertNotNull(savedToken.getToken());
        assertEquals(user, savedToken.getUser());

        ArgumentCaptor<String> contentCaptor = ArgumentCaptor.forClass(String.class);
        verify(emailService).send(eq("user@example.com"), eq("Recuperación de contraseña"), contentCaptor.capture());
        assertTrue(contentCaptor.getValue().contains(savedToken.getToken()));
    }

    @Test
    void resetPassword_ShouldUpdatePasswordAndInvalidateToken() {
        String token = "token123";
        User user = User.builder()
                .email("user@example.com")
                .build();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpirationDate(LocalDateTime.now().plusMinutes(30));

        when(tokenRepository.findByToken(token)).thenReturn(Optional.of(resetToken));
        when(passwordEncoder.encode("newpass")).thenReturn("encoded");

        authenticationService.resetPassword(token, "newpass");

        verify(passwordEncoder).encode("newpass");
        assertEquals("encoded", user.getPassword());
        verify(userRepository).save(user);
        verify(tokenRepository).delete(resetToken);
    }

    @Test
    void resetPassword_ShouldThrowException_WhenTokenNotFound() {
        when(tokenRepository.findByToken("bad")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> authenticationService.resetPassword("bad", "newpass"));
    }

    @Test
    void resetPassword_ShouldThrowException_WhenTokenExpired() {
        String token = "token123";
        User user = User.builder().email("user@example.com").build();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpirationDate(LocalDateTime.now().minusMinutes(1));

        when(tokenRepository.findByToken(token)).thenReturn(Optional.of(resetToken));

        assertThrows(IllegalArgumentException.class, () -> authenticationService.resetPassword(token, "newpass"));
        verify(userRepository, never()).save(any());
        verify(tokenRepository, never()).delete(any());
    }
}

