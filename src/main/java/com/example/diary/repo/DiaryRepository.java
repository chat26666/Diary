package com.example.diary.repo;
import com.example.diary.dto.*;
import com.example.diary.entity.Diary;

public interface DiaryRepository {

    DiaryResponseDto createDiary(Diary diary);
    void modifyDiary(Diary diary);
    String authPassword(Diary diary);
    void deleteDiary(Diary diary);
}
