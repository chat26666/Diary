package com.example.diary.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class DiaryFindPageRequestDto {

    @NotNull(message = "페이지를 입력해주세요.")
    @Range(min = 1, max = 10, message = "페이지 입력값은 1 이상 10 이하이어야 합니다.")
    private Integer page;
    @NotNull(message = "사이즈 크기를 입력해주세요.")
    @Range(min = 1, max = 15, message = "사이즈 입력값은 1 이상 15 이하이어야 합니다.")             //지나치게 큰 용량의 Select 는 DB에 영향이 갈 가능성이 있어서 적게 잡았습니다
    private Integer size;                                                                     //악의적인 용도로 한꺼번에 수십만건 이상 Select 를 방지합니다
}
