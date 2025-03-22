package com.example.diary.service;
import com.example.diary.dto.WriterRequestDto;
import com.example.diary.dto.WriterResponseDto;

public interface WriterService {

    WriterResponseDto createUser(WriterRequestDto dto);
    void deleteUser(Integer writerId,WriterRequestDto dto);
}
