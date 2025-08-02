package com.reservastrenque.reservas_trenque.users.service;

import com.reservastrenque.reservas_trenque.users.domain.Role;
import com.reservastrenque.reservas_trenque.users.domain.User;
import com.reservastrenque.reservas_trenque.users.repository.UserRepository;
import com.reservastrenque.reservas_trenque.users.usecase.ToggleAdminUserCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToggleAdminUserCaseImpl implements ToggleAdminUserCase {

    private final UserRepository userRepository;

    @Override
    public void execute(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El usuario que desea actualizar, no fue encontrado en el sistema"));
        if (user.getRole() == Role.ADMIN) {
            user.setRole(Role.USER);
        } else {
            user.setRole(Role.ADMIN);
        }
        userRepository.save(user);
    }
}

