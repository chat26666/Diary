package com.example.diary.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor   //해당 어노테이션이 없을 경우, @RequestBody 사용시 생성자 초기화가 안되어 문제가 생기기 때문에 추가하였습니다
public class RequestCreateDto {

    @NotBlank(message = "이름을 입력해주세요.")      //모든 필드는 전부 필수처리
    @Size(max = 30, message = "이름은 최대 30글자까지 가능합니다.")
    String name;

    @NotBlank(message = "패스워드를 입력해주세요.")
    String password;                              //패스워드는 해시값으로 저장되서 사이즈(고정 사이즈) 지정이 굳이 필요하지않다고 생각했습니다

    @NotBlank(message = "일정을 기입해주세요.")
    @Size(max = 200, message = "일정은 최대 200글자까지 가능합니다.")
    String plan;
}
