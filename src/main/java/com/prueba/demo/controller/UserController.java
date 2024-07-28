package com.prueba.demo.controller;

import com.prueba.demo.bl.UserBl;
import com.prueba.demo.dto.ResponseDto;
import com.prueba.demo.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserBl userService;

    @GetMapping
    public ResponseDto<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseDto<>("success", "Users retrieved successfully", users);
    }

    @GetMapping("/{id}")
    public ResponseDto<UserDto> getUserById(@PathVariable String id) {
        UserDto user = userService.getUserById(id);
        if (user != null) {
            return new ResponseDto<>("success", "User retrieved successfully", user);
        } else {
            return new ResponseDto<>("error", "User not found", null);
        }
    }

    @PostMapping
    public ResponseDto<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseDto<>("success", "User created successfully", createdUser);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return new ResponseDto<>("success", "User deleted successfully", null);
    }
}
