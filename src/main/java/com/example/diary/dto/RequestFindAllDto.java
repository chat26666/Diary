package com.example.diary.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class RequestFindAllDto {
    String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate updatedAt;
}
