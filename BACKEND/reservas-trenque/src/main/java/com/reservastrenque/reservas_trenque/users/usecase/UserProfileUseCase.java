package com.reservastrenque.reservas_trenque.users.usecase;

import com.reservastrenque.reservas_trenque.users.dto.UpdateUserProfileRequest;
import com.reservastrenque.reservas_trenque.users.dto.UserDTO;

public interface UserProfileUseCase {
    UserDTO completeProfile(Long userId, UpdateUserProfileRequest request);
}
