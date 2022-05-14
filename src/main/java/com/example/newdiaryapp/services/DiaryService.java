package com.example.newdiaryapp.services;

import com.example.newdiaryapp.models.Diary;
import com.example.newdiaryapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface DiaryService {
    Diary createDiary(String title, User user);
}
