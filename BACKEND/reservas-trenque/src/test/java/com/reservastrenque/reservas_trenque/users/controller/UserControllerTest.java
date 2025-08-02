package com.reservastrenque.reservas_trenque.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reservastrenque.reservas_trenque.users.dto.CreateUserRequest;
import com.reservastrenque.reservas_trenque.users.dto.UserDTO;
import com.reservastrenque.reservas_trenque.users.usecase.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean private GetAllUsersUseCase getAllUsersUseCase;
    @MockBean private GetUserByIdUseCase getUserByIdUseCase;
    @MockBean private RegisterUserUseCase registerUserUseCase;
    @MockBean private DeleteUserUseCase deleteUserUseCase;
    @MockBean private UpdateUserUseCase updateUserUseCase;
    @MockBean private ToggleStatusUserCase toggleStatusUserCase;
    @MockBean private ChangePasswordUseCase changePasswordUseCase;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void registerUserReturnsCreatedStatus() throws Exception {
        when(registerUserUseCase.execute(any(CreateUserRequest.class))).thenReturn(new UserDTO());

        CreateUserRequest request = CreateUserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .password("secret123")
                .build();

        mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
}
