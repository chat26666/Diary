package com.example.diary.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class DiaryResponseDto {
    private String name;
    private String title;
    private String plan;
}
