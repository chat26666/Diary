package com.example.diary.service;

import com.example.diary.dto.*;

import java.util.List;

public interface DiaryService {

    public ResponseCreateDto createDiary(Integer writerId,RequestCreateDto dto);
    public List<ResponseDataDto> getAllDiary(Integer writerId, RequestFindAllDto dto);
    public ResponseDataDto getDiary(Integer writerId, Integer DiaryId);
    public ResponseDataDto modifyDiary(Integer writerId, Integer DiaryId, RequestModifyDto dto);
}

