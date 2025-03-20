package com.example.diary.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseCreateDto {
    String name;
    String plan;
    Integer diaryId;
}
