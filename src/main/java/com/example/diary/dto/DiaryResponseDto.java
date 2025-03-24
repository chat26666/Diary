package com.example.diary.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)   //null 값 포함시 클라이언트한테 반환되지 않습니다
public class DiaryResponseDto {
    private String name;
    private String title;
    private String plan;
    private Integer diaryId;
}
