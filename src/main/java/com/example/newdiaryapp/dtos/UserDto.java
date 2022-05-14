package com.example.newdiaryapp.dtos;


import com.example.newdiaryapp.models.Diary;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("diaries")
public class UserDto {
    private Long id;
    private String email;
    private Set<Diary> diaries;
}
