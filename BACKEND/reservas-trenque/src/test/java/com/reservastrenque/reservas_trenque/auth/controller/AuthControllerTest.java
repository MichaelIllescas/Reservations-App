package com.reservastrenque.reservas_trenque.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reservastrenque.reservas_trenque.auth.dto.AuthenticationRequest;
import com.reservastrenque.reservas_trenque.auth.dto.AuthenticationResponse;
import com.reservastrenque.reservas_trenque.auth.service.AuthenticationService;
import com.reservastrenque.reservas_trenque.auth.service.CookieService;
import com.reservastrenque.reservas_trenque.users.domain.User;
import com.reservastrenque.reservas_trenque.users.dto.UserDTO;
import com.reservastrenque.reservas_trenque.users.service.UserMapper;
import com.reservastrenque.reservas_trenque.users.usecase.GetAuthenticatedUserUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean private AuthenticationManager authenticationManager;
    @MockBean private com.reservastrenque.reservas_trenque.auth.security.JwtService jwtService;
    @MockBean private UserDetailsService userDetailsService;
    @MockBean private AuthenticationService authenticationService;
    @MockBean private CookieService cookieService;
    @MockBean private GetAuthenticatedUserUseCase getAuthenticatedUserUseCase;
    @MockBean private UserMapper userMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void loginReturnsUserAndCookie() throws Exception {
        AuthenticationResponse authResponse = AuthenticationResponse.builder().jwt("jwt-token").build();
        when(authenticationService.login(any(AuthenticationRequest.class))).thenReturn(authResponse);
        when(cookieService.createAuthCookie("jwt-token")).thenReturn(ResponseCookie.from("authToken","jwt-token").build());

        User user = new User();
        user.setEmail("user@test.com");
        when(authenticationService.getUserByEmail("user@test.com")).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(new UserDTO());

        AuthenticationRequest request = new AuthenticationRequest("user@test.com","password");

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(header().exists("Set-Cookie"));
    }
}
