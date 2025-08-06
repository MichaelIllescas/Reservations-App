package com.reservastrenque.reservas_trenque.users.service;

import com.reservastrenque.reservas_trenque.users.domain.Role;
import com.reservastrenque.reservas_trenque.users.domain.User;
import com.reservastrenque.reservas_trenque.users.dto.CreateUserRequest;
import com.reservastrenque.reservas_trenque.users.dto.UserDTO;
import com.reservastrenque.reservas_trenque.users.repository.UserRepository;
import com.reservastrenque.reservas_trenque.users.usecase.RegisterUserUseCase;
import com.reservastrenque.reservas_trenque.users.usecase.SendConfirmationEmailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final SendConfirmationEmailUseCase sendConfirmationEmailUseCase;

    @Override
    public UserDTO execute(CreateUserRequest request) {
        // Validación: verificar si ya existe un usuario con ese email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario registrado con el email " + request.getEmail());
        }

        // Mapear los datos mínimos
        User newUser = userMapper.toUserCreate(request);

        // Setear datos temporales requeridos
        newUser.setDocumentNumber("PENDIENTE");
        newUser.setAddress("PENDIENTE");
        newUser.setEnabled(true);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        userRepository.save(newUser);

        // Enviar correo de confirmación de registro
        sendConfirmationEmailUseCase.execute(newUser.getEmail());

        return userMapper.toDTO(newUser);
    }

}
