package com.reservastrenque.reservas_trenque.users.service;

import com.reservastrenque.reservas_trenque.users.dto.UserDTO;
import com.reservastrenque.reservas_trenque.users.repository.UserRepository;
import com.reservastrenque.reservas_trenque.users.usecase.GetUserByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByIdUseCaseImpl implements GetUserByIdUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO execute(Long user_id) {
       return userMapper.toDTO(userRepository.getById(user_id));
    }
}
