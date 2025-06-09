package com.reservastrenque.reservas_trenque.users.usecase;

import com.reservastrenque.reservas_trenque.users.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface GetAuthenticatedUserUseCase {
    UserDTO execute(HttpServletRequest request);
}
