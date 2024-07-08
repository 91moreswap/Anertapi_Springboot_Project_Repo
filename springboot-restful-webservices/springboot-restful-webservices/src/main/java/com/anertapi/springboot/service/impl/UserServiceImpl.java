package com.anertapi.springboot.service.impl;

import com.anertapi.springboot.dto.UserDto;
import com.anertapi.springboot.entity.User;
import com.anertapi.springboot.exception.EmailAlreadyExistsException;
import com.anertapi.springboot.exception.ResourceNotFoundException;
import com.anertapi.springboot.mapper.AutoUserMapper;
import com.anertapi.springboot.repository.UserRepository;
import com.anertapi.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        //Convert UsedrDto into user JPA Entity
      // User user = UserMapper.mapToUser(userDto);
//    User user = modelMapper.map(userDto,User.class);

        Optional<User> optionalUser =userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for a given User");
        }

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);
        //Convert user JPA Entity into UserDto
      // UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

//        UserDto savedUserDto = modelMapper.map(savedUser,UserDto.class);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

        return savedUserDto;
    }

    @Override
    public Optional<UserDto> getUserById(Long userId) {
       User user = userRepository.findById(userId).orElseThrow(
               ()-> new ResourceNotFoundException("User","id",userId)
       );

      // return Optional.of(UserMapper.mapToUserDto(user));

//        return Optional.of(modelMapper.map(user,UserDto.class));
        return Optional.ofNullable(AutoUserMapper.MAPPER.mapToUserDto(user));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
       // return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());

//        return users.stream().map(user -> modelMapper.map(user,UserDto.class))
//                .collect(Collectors.toList());

        return users.stream().map(AutoUserMapper.MAPPER::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
       User existingUser = userRepository.findById(user.getId()).orElseThrow(
               ()-> new ResourceNotFoundException("User","id", user.getId())
       );
       existingUser.setFirstName(user.getFirstName());
       existingUser.setLastName(user.getLastName());
       existingUser.setEmail(user.getEmail());
      User updateUser = userRepository.save(existingUser);
//        return UserMapper.mapToUserDto(updateUser);

//        return modelMapper.map(updateUser,UserDto.class);

        return AutoUserMapper.MAPPER.mapToUserDto(updateUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User","id", userId)
        );

        userRepository.deleteById(userId);
    }
}
