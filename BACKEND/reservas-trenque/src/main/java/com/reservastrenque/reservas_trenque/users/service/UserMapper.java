package com.reservastrenque.reservas_trenque.users.service;

import com.reservastrenque.reservas_trenque.users.domain.Role;
import com.reservastrenque.reservas_trenque.users.domain.User;
import com.reservastrenque.reservas_trenque.users.dto.CreateUserRequest;
import com.reservastrenque.reservas_trenque.users.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {
    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .documentNumber((user.getDocumentNumber()))
                .phone(user.getPhone())
                .address(user.getAddress())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .enabled(user.isEnabled())
                .role(user.getRole())
                .registrationDate(user.getRegistrationDate())
                .build();
    }


    public User toUser(UserDTO userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .documentNumber((userDto.getDocumentNumber()))
                .phone(userDto.getPhone())
                .address(userDto.getAddress())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .enabled(userDto.getEnabled())
                .role(userDto.getRole())
                .build();
    }

    public User toUserCreate(CreateUserRequest userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(Role.USER)
                .phone(userDto.getPhone() == null || userDto.getPhone().isBlank() ? "PENDIENTE" : userDto.getPhone())
                .enabled(true)
                .build();
    }





}
