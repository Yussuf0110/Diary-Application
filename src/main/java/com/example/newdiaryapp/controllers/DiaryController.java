package com.example.newdiaryapp.controllers;


import com.example.newdiaryapp.controllers.responses.ApiResponse;
import com.example.newdiaryapp.exceptions.DiaryAppApplicationException;
import com.example.newdiaryapp.models.Diary;
import com.example.newdiaryapp.models.User;
import com.example.newdiaryapp.services.DiaryService;
import com.example.newdiaryapp.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/v3/diaryApp/diaries")
@Slf4j
public class DiaryController {
    private DiaryService diaryService;
    private UserService userService;

    @Autowired
    public DiaryController(DiaryService diaryService, UserService userService ) {
        this.diaryService = diaryService;
        this.userService = userService;
    }

    @PostMapping("/create/{userId}")
    private ResponseEntity<?> createDiary(@Valid @NotNull @NotBlank @PathVariable("userId") String userId, @NotNull @NotBlank @RequestParam String title) {
        log.info("User Service --> {}",userService);
        try {
            User user = userService.findById(Long.valueOf(userId));
            Diary diary = diaryService.createDiary(title, user);
            Diary savedDiary = userService.addDiary(Long.valueOf(userId), diary);
            ApiResponse apiResponse = ApiResponse.builder()
                    .payload(savedDiary)
                    .isSuccessful(true)
                    .message("diary added successfully")
                    .statusCode(201)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (DiaryAppApplicationException e) {
            ApiResponse apiResponse = ApiResponse.builder()
                    .message(e.getMessage())
                    .isSuccessful(false)
                    .statusCode(404)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
