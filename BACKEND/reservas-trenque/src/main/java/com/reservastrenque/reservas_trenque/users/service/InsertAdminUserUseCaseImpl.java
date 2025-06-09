package com.reservastrenque.reservas_trenque.users.service;

import com.reservastrenque.reservas_trenque.users.domain.Role;
import com.reservastrenque.reservas_trenque.users.domain.User;
import com.reservastrenque.reservas_trenque.users.repository.UserRepository;
import com.reservastrenque.reservas_trenque.users.usecase.InsertAdminUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class InsertAdminUserUseCaseImpl implements InsertAdminUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void execute() {
        Optional<User> existingUser = userRepository.findByEmail("admin@admin.com");

        if (existingUser.isEmpty()) {
            User admin = User.builder()
                    .firstName("Jonathan y Jose")
                    .lastName("Imperial-net")
                    .documentNumber("37757084")
                    .phone("2923530179")
                    .address("Trenque Lauquen")
                    .email("admin@admin.com")
                    .password(passwordEncoder.encode("admin.123"))
                    .role(Role.ADMIN)
                    .enabled(true)
                    .build();

            userRepository.save(admin);
        }
    }
}
