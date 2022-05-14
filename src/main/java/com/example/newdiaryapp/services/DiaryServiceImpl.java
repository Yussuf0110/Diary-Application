package com.example.newdiaryapp.services;

import com.example.newdiaryapp.models.Diary;
import com.example.newdiaryapp.models.User;
import com.example.newdiaryapp.repositories.DiaryRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@NoArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    DiaryRepository diaryRepository;
    @Override
    public Diary createDiary(String title, User user) {
        Diary diary = new Diary(title);
        diary.setUser(user);
        return diaryRepository.save(diary);
    }
}
