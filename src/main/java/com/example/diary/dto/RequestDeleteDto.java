package com.example.diary.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestDeleteDto {

    @NotBlank(message = "패스워드를 입력해주세요.")
    private String password;
    //멤버변수는 하나뿐이지만 Dto를 사용하는 것이 추후 확장성 부분에서 더 이득이 있지않을까 싶어서 만들었습니다
}
