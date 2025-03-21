package com.example.diary.service;

import com.example.diary.dto.*;

import java.util.List;

public interface DiaryService {

    ResponseCreateDto createDiary(Integer writerId,RequestCreateDto dto);
    List<ResponseDataDto> getAllDiary(Integer writerId, RequestFindAllDto dto);
    ResponseDataDto getDiary(Integer writerId, Integer DiaryId);
    ResponseDataDto modifyDiary(Integer writerId, Integer DiaryId, RequestModifyDto dto);
    void deleteDiary(Integer writerId, Integer DiaryId, RequestDeleteDto dto);
    List<ResponseDataDto> getPageDiary(Integer writerId, RequestFindPageDto dto);
}

