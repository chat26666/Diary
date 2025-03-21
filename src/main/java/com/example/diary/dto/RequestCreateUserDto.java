package com.example.diary.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestCreateUserDto {

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(max = 30, message = "이름은 최대 30글자까지 가능합니다.")
    String name;

    @Size(max = 50, message = "이메일은 최대 50글자까지 가능합니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    String email;
}
