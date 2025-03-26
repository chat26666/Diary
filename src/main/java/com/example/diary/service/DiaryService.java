package com.example.diary.service;
import com.example.diary.dto.*;
import com.example.diary.entity.Diary;
import java.util.List;

public interface DiaryService {

    void verifyDiaryPassword(Diary diary);
    DiaryResponseDto createDiary(Integer writerId, DiarySaveRequestDto dto);
    List<DiaryResponseDto> getAllDiary(Integer writerId, DiaryFindAllRequestDto dto);
    DiaryResponseDto getDiary(Integer writerId, Integer diaryId);
    void modifyDiary(Integer writerId, Integer diaryId, DiarySaveRequestDto dto);
    void deleteDiary(Integer writerId, Integer diaryId, DiaryDeleteRequestDto dto);
    List<DiaryResponseDto> getPageDiary(Integer writerId, DiaryFindPageRequestDto dto);
}

