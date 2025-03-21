package com.example.diary.service;

import com.example.diary.dto.RequestCreateUserDto;
import com.example.diary.dto.ResponseCreateUserDto;

public interface WriterService {

    ResponseCreateUserDto createUser(RequestCreateUserDto dto);
}
