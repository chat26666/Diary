package com.example.diary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Writer {
    private Integer writerId;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Diary> diaries;    //DB스키마와 유사하게 1대 다수(N)의 관계성을 최대한 자바 코드에서도 비슷하게 그려냈습니다
}                                   //계층간 데이터 전송을 전부 dto 로 해결한 탓에 사용되지는 않았습니다


