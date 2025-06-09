package com.reservastrenque.reservas_trenque.auth.controller;


import com.reservastrenque.reservas_trenque.auth.dto.AuthenticationRequest;
import com.reservastrenque.reservas_trenque.auth.dto.AuthenticationResponse;
import com.reservastrenque.reservas_trenque.auth.dto.EmailDTO;
import com.reservastrenque.reservas_trenque.auth.dto.ResetPasswordDTO;
import com.reservastrenque.reservas_trenque.auth.exceptions.DisabledUserException;
import com.reservastrenque.reservas_trenque.auth.exceptions.InvalidCredentialsException;
import com.reservastrenque.reservas_trenque.auth.security.JwtService;
import com.reservastrenque.reservas_trenque.auth.service.AuthenticationService;
import com.reservastrenque.reservas_trenque.auth.service.CookieService;
import com.reservastrenque.reservas_trenque.config.exception.ErrorResponseDTO;
import com.reservastrenque.reservas_trenque.users.dto.UserDTO;
import com.reservastrenque.reservas_trenque.users.service.UserMapper;
import com.reservastrenque.reservas_trenque.users.usecase.GetAuthenticatedUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Operaciones relacionadas con la autenticación en el sistema")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationService authenticationService;
    private final CookieService cookieService;
    private final GetAuthenticatedUserUseCase getAuthenticatedUserUseCase;
    private final UserMapper userMapper;

    @Operation(summary = "Iniciar sesión")
    @ApiResponse(responseCode = "200", description = "Autenticación exitosa")
    @ApiResponse(responseCode = "401", description = "Credenciales inválidas",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    @ApiResponse(responseCode = "403", description = "Usuario deshabilitado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response
    ) {
        try {
            AuthenticationResponse authResponse = authenticationService.login(request);
            ResponseCookie cookie = cookieService.createAuthCookie(authResponse.getJwt());
            response.addHeader("Set-Cookie", cookie.toString());
            UserDTO userDto = userMapper.toDTO(authenticationService.getUserByEmail(request.getEmail()).get());
            return ResponseEntity.ok(userDto);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        } catch (DisabledUserException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error al conectarse al servidor."));
        }
    }

    @Operation(summary = "Cerrar sesión")
    @ApiResponse(responseCode = "200", description = "Sesión cerrada correctamente")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("authToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok(Map.of("message", "Logout exitoso"));
    }

    @Operation(summary = "Obtener información de la sesión actual")
    @ApiResponse(responseCode = "200", description = "Sesión activa")
    @ApiResponse(responseCode = "401", description = "No hay sesión activa o token inválido",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getSession(
            @Parameter(description = "Token JWT en cookie")
            @CookieValue(name = "authToken", required = false) String jwtToken,
            HttpServletRequest request) {

        if (jwtToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "No hay sesión activa"));
        }

        try {
            UserDTO user = getAuthenticatedUserUseCase.execute(request);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Sesión inválida"));
        }
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestBody EmailDTO request) {
        authenticationService.sendPasswordResetToken(request.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordDTO request) {
        authenticationService.resetPassword(request.getToken(), request.getNewPassword());
        return ResponseEntity.ok().build();
    }
}
