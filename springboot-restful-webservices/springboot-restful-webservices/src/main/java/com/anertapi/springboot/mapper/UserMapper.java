package com.anertapi.springboot.mapper;

import com.anertapi.springboot.dto.UserDto;
import com.anertapi.springboot.entity.User;

public class UserMapper {
    //Convert User JPA Entity to userDto
    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDto;
    }

    //Convert userDto to User JPA Entity
    public static User mapToUser(UserDto userDto){
        User user =new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        return user;
    }
}
