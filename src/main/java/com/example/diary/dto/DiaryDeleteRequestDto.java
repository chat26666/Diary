package com.example.diary.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiaryDeleteRequestDto {

    @NotBlank(message = "패스워드를 입력해주세요.")
    private String password;

}
