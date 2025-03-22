package com.example.diary.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Writer {
    private Integer writerId;
    private String name;
    private String email;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}


