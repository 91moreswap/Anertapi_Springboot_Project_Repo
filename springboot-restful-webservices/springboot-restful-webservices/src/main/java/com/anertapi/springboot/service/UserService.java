package com.anertapi.springboot.service;

import com.anertapi.springboot.dto.UserDto;
import com.anertapi.springboot.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto createUser(UserDto user);

    Optional<UserDto> getUserById(Long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto user);
    void deleteUser(Long userId);
}
