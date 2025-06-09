package com.reservastrenque.reservas_trenque.users.usecase;

import com.reservastrenque.reservas_trenque.users.dto.CreateUserRequest;
import com.reservastrenque.reservas_trenque.users.dto.UserDTO;

public interface RegisterUserUseCase {
    UserDTO execute(CreateUserRequest request);
}
