package com.example.diary.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor   //해당 어노테이션이 없을 경우, @RequestBody 사용시 생성자 초기화가 안되어 문제가 생기기 때문에 추가하였습니다
public class RequestCreateDto {
    String name;
    String password;
    String plan;
    Integer WriterId;
}
