package com.example.diary.service;
import com.example.diary.dto.WriterCreateRequestDto;
import com.example.diary.dto.WriterCreateResponseDto;

public interface WriterService {
    WriterCreateResponseDto createUser(WriterCreateRequestDto dto);
}
