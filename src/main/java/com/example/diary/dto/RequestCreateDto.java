package com.example.diary.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCreateDto {
    String name;
    String plan;
    Integer diaryId;
    Integer WriterId;
}
