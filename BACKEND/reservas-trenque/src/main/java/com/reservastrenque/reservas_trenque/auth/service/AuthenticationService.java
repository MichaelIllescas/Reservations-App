/**
 * Servicio encargado de gestionar la autenticación de usuarios,
 * validando credenciales y generando tokens JWT.
 */
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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordResetTokenRepository tokenRepository;
    private final EmailService emailService;

    @Value("${app.frontend.reset-password-url}")
    private String resetPasswordBaseUrl;

    /**
     * Realiza el proceso de inicio de sesión validando credenciales
     * y generando un token JWT si el usuario es válido.
     *
     * @param request objeto con email y contraseña
     * @return respuesta con el token JWT generado
     * @throws InvalidCredentialsException si el usuario no existe o la contraseña es incorrecta
     * @throws DisabledUserException si el usuario está deshabilitado
     */
    public AuthenticationResponse login(AuthenticationRequest request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Usuario no encontrado."));

        if (!user.isEnabled()) {
            throw new DisabledUserException("El usuario está deshabilitado.");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new InvalidCredentialsException("Contraseña incorrecta.");
        }

        Map<String, Object> claims = new HashMap<>();
        String jwt = jwtService.generateToken(claims, user);

        return AuthenticationResponse.builder()
                .jwt(jwt)
                .build();
    }

    /**
     * Busca un usuario por su dirección de email.
     *
     * @param email email del usuario
     * @return usuario encontrado (opcional)
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void sendPasswordResetToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        //  Buscar token existente
        tokenRepository.findByUser(user).ifPresent(existingToken -> {
            if (existingToken.getExpirationDate().isBefore(LocalDateTime.now())) {
                tokenRepository.delete(existingToken); // está vencido, lo borro
            } else {
                throw new IllegalStateException("Ya hay un token activo para este usuario.");
            }
        });

        //  Generar y guardar nuevo token
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpirationDate(LocalDateTime.now().plusMinutes(30));

        tokenRepository.save(resetToken);

        emailService.send(
                user.getEmail(),
                "Recuperación de contraseña",
                "Haz clic en el siguiente enlace para restablecer tu contraseña:\n" +
                        resetPasswordBaseUrl + "?token=" + token
        );
    }

    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token inválido"));

        if (resetToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token expirado");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        tokenRepository.delete(resetToken); // Invalida el token
    }

}
