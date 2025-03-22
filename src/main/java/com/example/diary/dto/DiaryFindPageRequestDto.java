package com.example.diary.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryFindPageRequestDto {

    @NotNull(message = "페이지를 입력해주세요.")
    private Integer page;
    @NotNull(message = "페이지 크기를 입력해주세요.")
    private Integer size;
}
