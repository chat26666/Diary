package com.example.diary.repo;
import com.example.diary.dto.*;
import com.example.diary.entity.Diary;

import java.util.List;

public interface DiaryRepository {

    DiaryCreateResponseDto createDiary(Diary diary);
    List<DiaryResponseDto> getAllDiary(Diary diary);
    DiaryResponseDto getDiary(Diary diary);
    void modifyDiary(Diary diary);
    String authPassword(Diary diary);
    void deleteDiary(Diary diary);
    List<DiaryResponseDto> getPageDiary(Integer writerId, DiaryFindPageRequestDto dto,int page,int size);
}
