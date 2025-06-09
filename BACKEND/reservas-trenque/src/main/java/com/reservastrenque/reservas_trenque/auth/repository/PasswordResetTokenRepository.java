package com.reservastrenque.reservas_trenque.auth.repository;

import com.reservastrenque.reservas_trenque.auth.entity.PasswordResetToken;
import com.reservastrenque.reservas_trenque.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    void deleteByUser(User user);
    Optional<PasswordResetToken> findByUser(User user);

}
