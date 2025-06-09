package com.reservastrenque.reservas_trenque.users.service;

import com.reservastrenque.reservas_trenque.users.domain.User;
import com.reservastrenque.reservas_trenque.users.dto.UserDTO;
import com.reservastrenque.reservas_trenque.users.repository.UserRepository;
import com.reservastrenque.reservas_trenque.users.usecase.ToggleStatusUserCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class ToggleStatusUserCaseImpl implements ToggleStatusUserCase {

    private final UserRepository userRepository;


    @Override
    public void execute(Long id) {
        User user= userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("El usuario que desea actualizar, no fue encontrado en el sistema"));
        if (user.isEnabled()){
            user.setEnabled(false);
        }else {
            user.setEnabled(true);
        }
        userRepository.save(user);
    }
}
