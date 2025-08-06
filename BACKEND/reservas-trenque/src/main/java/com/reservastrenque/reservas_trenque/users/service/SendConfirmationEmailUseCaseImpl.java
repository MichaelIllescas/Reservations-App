package com.reservastrenque.reservas_trenque.users.service;

import com.reservastrenque.reservas_trenque.auth.service.EmailService;
import com.reservastrenque.reservas_trenque.users.domain.User;
import com.reservastrenque.reservas_trenque.users.repository.UserRepository;
import com.reservastrenque.reservas_trenque.users.usecase.SendConfirmationEmailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendConfirmationEmailUseCaseImpl implements SendConfirmationEmailUseCase {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Value("${app.frontend.login-url}")
    private String loginUrl;

    @Override
    public void execute(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        String subject = "Confirmación de registro";
        String content = String.format(
                "Hola %s %s,\n\nTu registro fue exitoso.\n\nNombre de usuario: %s %s\nCorreo electrónico: %s\n\nPuedes iniciar sesión en tu cuenta aquí: %s\n\nSi no solicitaste este registro, ignora este mensaje.",
                user.getFirstName(),
                user.getLastName(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                loginUrl
        );

        emailService.send(user.getEmail(), subject, content);
    }
}
