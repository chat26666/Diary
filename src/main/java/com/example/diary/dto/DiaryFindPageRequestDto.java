package com.example.diary.dto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryFindPageRequestDto {

    @NotNull(message = "페이지를 입력해주세요.")
    @Min(value = 1, message = "페이지 최소 입력값은 1 이상이어야 합니다.")
    @Max(value = 10, message = "페이지 입력값은 최대 10 입니다.")
    private Integer page;
    @NotNull(message = "사이즈 크기를 입력해주세요.")
    @Min(value = 1, message = "사이즈 최소 입력값은 1 이상이어야 합니다.")
    @Max(value = 15, message = "사이즈 최대 입력값은 최대 15 입니다.")              //지나치게 큰 용량의 Select 는 DB에 영향이 갈 가능성이 있어서 적게 잡았습니다
    private Integer size;                                                      //악의적인 용도로 한꺼번에 수십만건 이상 Select 를 방지합니다
}
