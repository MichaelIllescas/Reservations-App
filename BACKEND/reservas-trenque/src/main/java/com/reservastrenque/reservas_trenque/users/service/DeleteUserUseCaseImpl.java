package com.reservastrenque.reservas_trenque.users.service;

import com.reservastrenque.reservas_trenque.users.repository.UserRepository;
import com.reservastrenque.reservas_trenque.users.usecase.DeleteUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepository userRepository;


    @Override
    public void execute(Long user_id) {
        if (!userRepository.existsById(user_id)) {
            throw new NoSuchElementException("El usuario con ID " + user_id + " no existe en el sistema");
        }

        userRepository.deleteById(user_id);

    }
}
