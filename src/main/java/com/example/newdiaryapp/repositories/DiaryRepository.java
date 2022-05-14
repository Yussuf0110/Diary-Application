package com.example.newdiaryapp.repositories;

import com.example.newdiaryapp.models.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

}
