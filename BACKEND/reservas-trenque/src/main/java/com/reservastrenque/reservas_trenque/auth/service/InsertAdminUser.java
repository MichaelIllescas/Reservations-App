package com.reservastrenque.reservas_trenque.auth.service;


import com.example.backend.users.service.usecase.InsertAdminUserUseCase;
import com.reservastrenque.reservas_trenque.users.domain.Role;
import com.reservastrenque.reservas_trenque.users.domain.User;
import com.reservastrenque.reservas_trenque.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InsertAdminUser implements InsertAdminUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Inserta un usuario administrador si no existe previamente.
     * Se utiliza para crear un usuario administrador inicial en la base de datos.
     */

    @Override
    public void execute(){
        Optional<User> existingUser = userRepository.findByEmail("admin@admin.com");

        if (existingUser.isEmpty()) {
            User admin = User.builder()
                    .email("admin@admin.com")
                    .enabled(true)
                    .address("TL")
                    .phone("2923530179")
                    .firstName("Jonathan")
                    .lastName("Illescas")
                    .documentNumber("37757084")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .enabled(true)
                    .build();
            userRepository.save(admin);
        }
    }
}
