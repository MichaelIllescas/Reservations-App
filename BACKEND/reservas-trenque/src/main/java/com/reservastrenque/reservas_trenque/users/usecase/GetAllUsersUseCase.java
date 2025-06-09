package com.reservastrenque.reservas_trenque.users.usecase;

import com.reservastrenque.reservas_trenque.users.dto.UserDTO;

import java.util.List;

public interface GetAllUsersUseCase {

    List<UserDTO> execute();
}
