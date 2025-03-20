package com.example.diary.repo;
import com.example.diary.dto.RequestCreateDto;
import com.example.diary.dto.ResponseCreateDto;

public interface DiaryRepository {

    public ResponseCreateDto createDiary(Integer writerId, RequestCreateDto dto);

}
