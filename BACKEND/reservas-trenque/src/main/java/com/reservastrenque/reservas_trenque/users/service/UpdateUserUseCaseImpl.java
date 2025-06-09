package com.reservastrenque.reservas_trenque.users.service;

import com.reservastrenque.reservas_trenque.users.domain.Role;
import com.reservastrenque.reservas_trenque.users.domain.User;
import com.reservastrenque.reservas_trenque.users.dto.UserDTO;
import com.reservastrenque.reservas_trenque.users.repository.UserRepository;
import com.reservastrenque.reservas_trenque.users.usecase.UpdateUserUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO execute(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("El usuario que estás intentando actualizar no fue encontrado en el sistema."));

        // Verificar si otro usuario ya tiene el mismo email
        userRepository.findByEmail(userDTO.getEmail()).ifPresent(existingUser -> {
            if (!existingUser.getId().equals(user.getId())) {
                throw new RuntimeException("Ya hay un usuario registrado con el email: " + userDTO.getEmail());
            }
        });

        // Verificar si otro usuario ya tiene el mismo documento
        userRepository.findByDocumentNumber(userDTO.getDocumentNumber()).ifPresent(existingUser -> {
            if (!existingUser.getId().equals(user.getId())) {
                throw new RuntimeException("Ya hay un usuario registrado con el documento: " + userDTO.getDocumentNumber());
            }
        });

        mergeUser(userDTO, user);

        return userMapper.toDTO(userRepository.save(user));
    }

    private void mergeUser(UserDTO dto, User user) {
        if (dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) user.setLastName(dto.getLastName());
        if (dto.getDocumentNumber() != null) user.setDocumentNumber(dto.getDocumentNumber());
        if (dto.getPhone() != null) user.setPhone(dto.getPhone());
        if (dto.getAddress() != null) user.setAddress(dto.getAddress());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getEnabled() != null) user.setEnabled(dto.getEnabled());
        if (dto.getRole() != null) user.setRole(dto.getRole());
    }

}
