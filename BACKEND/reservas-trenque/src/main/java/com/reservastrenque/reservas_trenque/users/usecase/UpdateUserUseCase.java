package com.reservastrenque.reservas_trenque.users.usecase;

import com.reservastrenque.reservas_trenque.users.domain.User;
import com.reservastrenque.reservas_trenque.users.dto.UserDTO;

public interface UpdateUserUseCase {

    UserDTO execute(UserDTO userDTO);
}
