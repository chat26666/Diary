package com.example.diary.controller;
import com.example.diary.dto.*;
import com.example.diary.service.DiaryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/writers")
@AllArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    //일정생성 컨트롤러
    @PostMapping("/{writerId}/diaries")
    public ResponseEntity<ResponseCreateDto> createDiary(@PathVariable Integer writerId, @RequestBody @Valid RequestCreateDto dto){
        return new ResponseEntity<>(diaryService.createDiary(writerId,dto), HttpStatus.CREATED);
    }
    //사용자 ID,날짜,이름으로 전체 조회하는 컨트롤러
    @GetMapping("/{writerId}/diaries")
    public ResponseEntity<List<ResponseDataDto>> getAllDiary(@PathVariable Integer writerId, @ModelAttribute @Valid RequestFindAllDto dto) {
        return new ResponseEntity<>(diaryService.getAllDiary(writerId,dto), HttpStatus.OK);
    }
    //사용자 ID, 일정 ID로 검색하는 컨트롤러
    @GetMapping("/{writerId}/diaries/{diaryId}")
    public ResponseEntity<ResponseDataDto> getDiary(@PathVariable Integer writerId, @PathVariable Integer diaryId) {
        return new ResponseEntity<>(diaryService.getDiary(writerId,diaryId), HttpStatus.OK);
    }
    //일정 수정 컨트롤러
    @PutMapping("/{writerId}/diaries/{diaryId}")
    public ResponseEntity<ResponseDataDto> modifyDiary(@PathVariable Integer writerId, @PathVariable Integer diaryId, @RequestBody @Valid RequestModifyDto dto) {
        return new ResponseEntity<>(diaryService.modifyDiary(writerId,diaryId,dto), HttpStatus.OK);
    }
    //일정 삭제 컨트롤러
    @DeleteMapping("/{writerId}/diaries/{diaryId}")
    public ResponseEntity<String> deleteDiary(@PathVariable Integer writerId, @PathVariable Integer diaryId,@RequestBody @Valid RequestDeleteDto dto) {
        diaryService.deleteDiary(writerId,diaryId,dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //페이지와 페이지 크기로 조회 컨트롤러
    @GetMapping("/{writerId}/diaries/page")
    public ResponseEntity<List<ResponseDataDto>> getPageDiary(@PathVariable Integer writerId, @ModelAttribute @Valid RequestFindPageDto dto ) {
        return new ResponseEntity<>(diaryService.getPageDiary(writerId,dto), HttpStatus.OK);
    }
}
