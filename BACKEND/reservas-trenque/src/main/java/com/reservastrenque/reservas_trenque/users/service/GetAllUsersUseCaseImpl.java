package com.reservastrenque.reservas_trenque.users.service;

import com.reservastrenque.reservas_trenque.users.dto.UserDTO;
import com.reservastrenque.reservas_trenque.users.repository.UserRepository;
import com.reservastrenque.reservas_trenque.users.usecase.GetAllUsersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUsersUseCaseImpl implements GetAllUsersUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> execute() {
       return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }
}
