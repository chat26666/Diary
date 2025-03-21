package com.example.diary.service;

import com.example.diary.dto.*;

import java.util.List;

public interface DiaryService {

    DiaryCreateResponseDto createDiary(Integer writerId, DiarySaveRequestDto dto);
    List<DiaryResponseDto> getAllDiary(Integer writerId, DiaryFindAllRequestDto dto);
    DiaryResponseDto getDiary(Integer writerId, Integer DiaryId);
    DiaryResponseDto modifyDiary(Integer writerId, Integer DiaryId, DiarySaveRequestDto dto);
    void deleteDiary(Integer writerId, Integer DiaryId, DiaryDeleteRequestDto dto);
    List<DiaryResponseDto> getPageDiary(Integer writerId, DiaryFindPageRequestDto dto);
}

