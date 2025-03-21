package com.example.diary.repo;
import com.example.diary.dto.RequestCreateUserDto;
import com.example.diary.dto.ResponseCreateUserDto;

public interface WriterRepository {

    ResponseCreateUserDto createUser(RequestCreateUserDto dto);

}
