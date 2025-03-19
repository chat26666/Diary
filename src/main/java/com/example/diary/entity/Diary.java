package com.example.diary.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
public class Diary {
    private Integer diaryId;
    private Integer writerId;
    private String name;
    private String plan;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
