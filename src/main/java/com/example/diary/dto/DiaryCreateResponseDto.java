package com.example.diary.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DiaryCreateResponseDto {
    private String title;
    private String plan;
    private Integer diaryId;
}
