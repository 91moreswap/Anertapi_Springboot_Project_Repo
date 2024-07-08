package com.anertapi.springboot.controller;

import com.anertapi.springboot.dto.UserDto;
import com.anertapi.springboot.entity.User;
import com.anertapi.springboot.exception.ErrorDetails;
import com.anertapi.springboot.exception.ResourceNotFoundException;
import com.anertapi.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    //Build create User POST REST API
    //http://localhost:8080/api/users
    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping()
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
       UserDto savedUser= userService.createUser(user);
       return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    //Build GET userById REST API
    //http://localhost:8080/api/users/2
    @Operation(
            summary = "Get User by ID REST API",
            description = "Get User by ID REST API is used to get single user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<Optional<UserDto>> getUserById(@PathVariable("id") Long userId){
       Optional<UserDto> user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    //Build Get All Users REST API
    //http://localhost:8080/api/users
    @Operation(
            summary = "Get All Users REST API",
            description = "Get All Users REST API is used to get all the Users from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
       List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //Build PUT REST API for Update the User
    //http://localhost:8080/api/users/update/1
    @Operation(
            summary = "Update User REST API",
            description = "Update User by ID REST API is used to update particular user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("update/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto  user,@PathVariable("id") Long userId){
        user.setId(userId);
       UserDto updateUser = userService.updateUser(user);
       return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    //Build DELETE REST API
    //http://localhost:8080/api/users/delete/1

    @Operation(
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete particular user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User Successfully Deleted!",HttpStatus.OK);
    }
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
//                                                                        WebRequest webRequest){
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "USER NOT FOUND"
//        );
//        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//    }
}
