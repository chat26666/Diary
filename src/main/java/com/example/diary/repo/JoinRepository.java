package com.example.diary.repo;
import com.example.diary.dto.DiaryFindPageRequestDto;
import com.example.diary.dto.DiaryResponseDto;
import com.example.diary.entity.Diary;

import java.util.List;

public interface JoinRepository {

    DiaryResponseDto getDiary(Diary diary);
    List<DiaryResponseDto> getPageDiary(Integer writerId, DiaryFindPageRequestDto dto, int page, int size);
    List<DiaryResponseDto> getAllDiary(Diary diary);
}
