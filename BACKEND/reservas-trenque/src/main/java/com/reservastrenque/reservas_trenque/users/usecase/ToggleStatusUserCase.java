package com.reservastrenque.reservas_trenque.users.usecase;

import com.reservastrenque.reservas_trenque.users.dto.UserDTO;

public interface ToggleStatusUserCase {

    void execute(Long id);
}
