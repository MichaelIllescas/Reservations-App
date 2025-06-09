package com.reservastrenque.reservas_trenque.users.usecase;

import com.reservastrenque.reservas_trenque.users.dto.ChangePasswordRequest;

public interface ChangePasswordUseCase {
    void execute(Long userId, ChangePasswordRequest request);
}
