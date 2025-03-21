package com.example.diary.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestModifyDto {
    @NotBlank(message = "이름을 입력해주세요.")
    @Size(max = 30, message = "이름은 최대 30글자까지 가능합니다.")
    String name;

    @NotBlank(message = "일정을 기입해주세요.")
    @Size(max = 200, message = "일정은 최대 200글자까지 가능합니다.")
    String plan;

    @NotBlank(message = "패스워드를 입력해주세요.")
    String password;
}
