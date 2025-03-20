package com.example.diary.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFindPageDto {
    Integer page;
    Integer size;
}
