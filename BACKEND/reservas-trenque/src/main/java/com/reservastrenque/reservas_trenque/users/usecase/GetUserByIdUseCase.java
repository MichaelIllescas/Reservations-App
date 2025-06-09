package com.reservastrenque.reservas_trenque.users.usecase;

import com.reservastrenque.reservas_trenque.users.dto.UserDTO;

public interface GetUserByIdUseCase {

    UserDTO execute(Long user_id);
}
