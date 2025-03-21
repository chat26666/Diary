package com.example.diary.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class DiaryFindAllRequestDto {

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(max = 30, message = "이름은 최대 30글자까지 가능합니다.")
    private String name;

    @NotNull(message = "날짜를 입력해주세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedAt;
}
