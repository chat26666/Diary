package com.example.diary.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class DiarySaveRequestDto {
    //모든 필드는 전부 필수입력처리
    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 30, message = "제목은 최대 30글자까지 가능합니다.")
    private String title;

    @NotBlank(message = "패스워드를 입력해주세요.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+{};:,<.>]).{8,}$",
            message = "비밀번호는 최소 8자 이상이며, 대문자, 소문자, 숫자, 특수문자를 모두 포함해야 합니다.",
            groups = CreateGroup.class
    )
    private String password;
    //패스워드는 해시값으로 저장되서 DB에 고정크기로 저장되기 때문에 사이즈 지정은 굳이 필요하지않다고 생각했습니다
    //다만 일정생성시에만 복잡도를 설정하게끔 하였습니다

    @NotBlank(message = "일정을 기입해주세요.")
    @Size(max = 200, message = "일정은 최대 200글자까지 가능합니다.")
    private String plan;
}
