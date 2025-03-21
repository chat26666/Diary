package com.example.diary.repo;
import com.example.diary.dto.*;

import java.util.List;

public interface DiaryRepository {

    ResponseCreateDto createDiary(Integer writerId, RequestCreateDto dto);
    List<ResponseDataDto> getAllDiary(Integer writerId,RequestFindAllDto dto);
    ResponseDataDto getDiary(Integer writerId,Integer diaryId);
    int modifyDiary(Integer writerId, Integer diaryId, RequestModifyDto dto);
    String authPassword(Integer writerId, Integer diaryId);
    int deleteDiary(Integer writerId, Integer diaryId, RequestDeleteDto dto);
    List<ResponseDataDto> getPageDiary(Integer writerId,RequestFindPageDto dto);
}
