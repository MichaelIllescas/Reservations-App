package com.reservastrenque.reservas_trenque.users.controller;

import com.reservastrenque.reservas_trenque.config.exception.ErrorResponseDTO;
import com.reservastrenque.reservas_trenque.users.dto.ChangePasswordRequest;
import com.reservastrenque.reservas_trenque.users.dto.CreateUserRequest;
import com.reservastrenque.reservas_trenque.users.dto.UserDTO;
import com.reservastrenque.reservas_trenque.users.usecase.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios del sistema")
public class UserController {

    private final GetAllUsersUseCase getAllUsersUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final ToggleStatusUserCase toggleStatusUserUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;

    @Operation(summary = "Obtener todos los usuarios")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(getAllUsersUseCase.execute());
    }

    @Operation(summary = "Obtener un usuario por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long user_id) {
        return ResponseEntity.ok(getUserByIdUseCase.execute(user_id));
    }

    @Operation(summary = "Registrar un nuevo usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario registrado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody CreateUserRequest request) {
        UserDTO createdUser = registerUserUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(summary = "Eliminar un usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.ok(Map.of("message", "Usuario eliminado correctamente"));
    }

    @Operation(summary = "Actualizar un usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(updateUserUseCase.execute(userDTO));
    }

    @Operation(summary = "Cambiar estado (activar/desactivar) de un usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/toggle-status/{id}")
    public ResponseEntity<Map<String, String>> toggleStatus(@PathVariable Long id) {
        toggleStatusUserUseCase.execute(id);
        return ResponseEntity.ok(Map.of("message", "El estado ha sido cambiado correctamente"));
    }



    @Operation(summary = "Cambiar la contraseña del usuario autenticado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contraseña cambiada correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/change-password")
    public ResponseEntity<Map<String, String>> changePassword(
            @Valid @RequestBody ChangePasswordRequest request,
            @Parameter(hidden = true) @RequestAttribute("userId") Long userId
    ) {
        changePasswordUseCase.execute(userId, request);
        return ResponseEntity.ok(Map.of("message", "Contraseña actualizada correctamente"));
    }

}
