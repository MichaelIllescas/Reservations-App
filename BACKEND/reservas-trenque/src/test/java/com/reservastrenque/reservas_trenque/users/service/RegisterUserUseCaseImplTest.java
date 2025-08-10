package com.reservastrenque.reservas_trenque.users.service;

import com.reservastrenque.reservas_trenque.users.domain.User;
import com.reservastrenque.reservas_trenque.users.dto.CreateUserRequest;
import com.reservastrenque.reservas_trenque.users.dto.UserDTO;
import com.reservastrenque.reservas_trenque.users.repository.UserRepository;
import com.reservastrenque.reservas_trenque.users.usecase.SendConfirmationEmailUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserMapper userMapper;
    @Mock
    private SendConfirmationEmailUseCase sendConfirmationEmailUseCase;

    @InjectMocks
    private RegisterUserUseCaseImpl registerUserUseCase;

    @Test
    void execute_ShouldSaveUserWithEncodedPasswordAndSendEmail_WhenSuccessful() {
        CreateUserRequest request = CreateUserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .password("plainPass")
                .phone("123456")
                .build();
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .password("plainPass")
                .build();
        UserDTO expectedDto = UserDTO.builder()
                .email("john@example.com")
                .build();

        when(userRepository.existsByEmail("john@example.com")).thenReturn(false);
        when(userMapper.toUserCreate(request)).thenReturn(user);
        when(passwordEncoder.encode("plainPass")).thenReturn("encodedPass");
        when(userMapper.toDTO(user)).thenReturn(expectedDto);

        UserDTO result = registerUserUseCase.execute(request);

        assertEquals("encodedPass", user.getPassword());
        assertEquals(expectedDto, result);
        verify(userRepository).save(user);
        verify(sendConfirmationEmailUseCase).execute("john@example.com");
    }

    @Test
    void execute_ShouldThrowIllegalArgumentException_WhenEmailAlreadyExists() {
        CreateUserRequest request = CreateUserRequest.builder()
                .email("john@example.com")
                .build();

        when(userRepository.existsByEmail("john@example.com")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> registerUserUseCase.execute(request));
        verify(userRepository, never()).save(any());
        verify(sendConfirmationEmailUseCase, never()).execute(any());
    }

    @Test
    void execute_ShouldReturnUserDTOFromMapper() {
        CreateUserRequest request = CreateUserRequest.builder()
                .firstName("Jane")
                .lastName("Doe")
                .email("jane@example.com")
                .password("secret")
                .build();
        User user = User.builder()
                .email("jane@example.com")
                .password("secret")
                .build();
        UserDTO expectedDto = UserDTO.builder()
                .email("jane@example.com")
                .firstName("Jane")
                .lastName("Doe")
                .build();

        when(userRepository.existsByEmail("jane@example.com")).thenReturn(false);
        when(userMapper.toUserCreate(request)).thenReturn(user);
        when(passwordEncoder.encode("secret")).thenReturn("enc");
        when(userMapper.toDTO(user)).thenReturn(expectedDto);

        UserDTO result = registerUserUseCase.execute(request);

        assertSame(expectedDto, result);
    }
}

