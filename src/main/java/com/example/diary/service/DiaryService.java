package com.example.diary.service;

import com.example.diary.dto.RequestCreateDto;
import com.example.diary.dto.ResponseCreateDto;

public interface DiaryService {

    public ResponseCreateDto createDiary(RequestCreateDto dto);
}

