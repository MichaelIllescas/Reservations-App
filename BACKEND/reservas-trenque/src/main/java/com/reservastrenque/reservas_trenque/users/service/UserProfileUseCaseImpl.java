package com.reservastrenque.reservas_trenque.users.usecase.impl;

import com.reservastrenque.reservas_trenque.users.domain.User;
import com.reservastrenque.reservas_trenque.users.dto.UpdateUserProfileRequest;
import com.reservastrenque.reservas_trenque.users.dto.UserDTO;
import com.reservastrenque.reservas_trenque.users.repository.UserRepository;
import com.reservastrenque.reservas_trenque.users.service.UserMapper;
import com.reservastrenque.reservas_trenque.users.usecase.UserProfileUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileUseCaseImpl implements UserProfileUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO completeProfile(Long userId, UpdateUserProfileRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + userId));

        user.setDocumentNumber(request.getDocumentNumber());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());

        userRepository.save(user);

        return userMapper.toDTO(user);
    }
}
