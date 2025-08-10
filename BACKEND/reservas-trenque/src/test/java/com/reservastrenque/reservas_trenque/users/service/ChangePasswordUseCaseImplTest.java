package com.reservastrenque.reservas_trenque.users.service;

import com.reservastrenque.reservas_trenque.users.domain.User;
import com.reservastrenque.reservas_trenque.users.dto.ChangePasswordRequest;
import com.reservastrenque.reservas_trenque.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChangePasswordUseCaseImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ChangePasswordUseCaseImpl changePasswordUseCase;

    @Test
    void execute_ShouldThrowException_WhenCurrentPasswordIncorrect() {
        ChangePasswordRequest request = new ChangePasswordRequest();
        request.setCurrentPassword("wrong");
        request.setNewPassword("newPass");
        request.setRepeatPassword("newPass");

        User user = User.builder().password("storedHash").build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong", "storedHash")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> changePasswordUseCase.execute(1L, request));

        verify(userRepository, never()).save(any());
        verify(passwordEncoder, never()).encode(anyString());
    }

    @Test
    void execute_ShouldThrowException_WhenNewPasswordsDoNotMatch() {
        ChangePasswordRequest request = new ChangePasswordRequest();
        request.setCurrentPassword("current");
        request.setNewPassword("newPass1");
        request.setRepeatPassword("newPass2");

        User user = User.builder().password("storedHash").build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("current", "storedHash")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> changePasswordUseCase.execute(1L, request));

        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any());
    }

    @Test
    void execute_ShouldEncodePasswordAndSaveUser_WhenPasswordsValid() {
        ChangePasswordRequest request = new ChangePasswordRequest();
        request.setCurrentPassword("current");
        request.setNewPassword("newPass");
        request.setRepeatPassword("newPass");

        User user = User.builder().password("oldHash").build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("current", "oldHash")).thenReturn(true);
        when(passwordEncoder.encode("newPass")).thenReturn("encodedNewPass");

        changePasswordUseCase.execute(1L, request);

        verify(passwordEncoder).encode("newPass");
        verify(userRepository).save(user);
        assertEquals("encodedNewPass", user.getPassword());
    }
}

