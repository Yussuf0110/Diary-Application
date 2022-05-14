package com.example.newdiaryapp.services;

import com.example.newdiaryapp.dtos.UserDto;
import com.example.newdiaryapp.exceptions.DiaryAppApplicationException;
import com.example.newdiaryapp.models.Diary;
import com.example.newdiaryapp.models.User;

import javax.validation.constraints.NotNull;

public interface UserService {
    UserDto createAccount(String email, String password) throws DiaryAppApplicationException;
    Diary addDiary(@NotNull Long id, @NotNull Diary diary) throws DiaryAppApplicationException;

    User findById(Long userId) throws DiaryAppApplicationException;

    boolean deleteUser(User user);
}
