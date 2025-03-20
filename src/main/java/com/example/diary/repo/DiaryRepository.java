package com.example.diary.repo;
import com.example.diary.dto.*;

import java.util.List;

public interface DiaryRepository {

    public ResponseCreateDto createDiary(Integer writerId, RequestCreateDto dto);
    public List<ResponseDataDto> getAllDiary(Integer writerId,RequestFindAllDto dto);
    public ResponseDataDto getDiary(Integer writerId,Integer diaryId);
    public int modifyDiary(Integer writerId, Integer diaryId, RequestModifyDto dto);
    public String authPassword(Integer writerId, Integer diaryId);
    public int deleteDiary(Integer writerId, Integer diaryId, RequestDeleteDto dto);
    public List<ResponseDataDto> getPageDiary(Integer writerId,RequestFindPageDto dto);
}
