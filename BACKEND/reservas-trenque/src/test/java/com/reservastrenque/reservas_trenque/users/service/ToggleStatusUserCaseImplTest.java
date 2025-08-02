package com.reservastrenque.reservas_trenque.users.service;

import com.reservastrenque.reservas_trenque.users.domain.User;
import com.reservastrenque.reservas_trenque.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ToggleStatusUserCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ToggleStatusUserCaseImpl toggleStatusUserCase;

    @Test
    void executeTogglesStatusAndSavesUser() {
        User user = new User();
        user.setEnabled(true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        toggleStatusUserCase.execute(1L);

        assertFalse(user.isEnabled());
        verify(userRepository).save(user);
    }
}
