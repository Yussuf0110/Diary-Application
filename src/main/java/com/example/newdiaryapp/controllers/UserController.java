package com.example.newdiaryapp.controllers;


import com.example.newdiaryapp.controllers.responses.ApiResponse;
import com.example.newdiaryapp.dtos.UserDto;
import com.example.newdiaryapp.exceptions.DiaryAppApplicationException;
import com.example.newdiaryapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/v3/diaryApp")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login(){
        return "Welcome to my application";
    }

    @PostMapping("/users/create")
    public ResponseEntity<?> createUser(@RequestParam @Valid @NotNull @NotBlank String email,
                                       @RequestParam @Valid @NotNull @NotBlank String password) throws DiaryAppApplicationException{
//        try {
            UserDto userDto = userService.createAccount(email, password);
            ApiResponse apiResponse = ApiResponse.builder()
                    .payload(userDto)
                    .isSuccessful(true)
                    .statusCode(201)
                    .message("user created successfully")
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
//        } catch (DiaryAppApplicationException e) {
//            ApiResponse apiResponse = ApiResponse.builder()
//                    .message(e.getMessage())
//                    .isSuccessful(false)
//                    .statusCode(400)
//                    .build();
//            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//        }
    }
}
