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
    public ResponseEntity<DiaryCreateResponseDto> createDiary(@PathVariable Integer writerId, @RequestBody @Valid DiarySaveRequestDto dto){
        return new ResponseEntity<>(diaryService.createDiary(writerId,dto), HttpStatus.CREATED);
    }
    //사용자 ID,날짜,이름으로 전체 조회하는 컨트롤러
    @GetMapping("/{writerId}/diaries")
    public ResponseEntity<List<DiaryResponseDto>> getAllDiary(@PathVariable Integer writerId, @ModelAttribute @Valid DiaryFindAllRequestDto dto) {
        return new ResponseEntity<>(diaryService.getAllDiary(writerId,dto), HttpStatus.OK);
    }
    //사용자 ID, 일정 ID로 검색하는 컨트롤러
    @GetMapping("/{writerId}/diaries/{diaryId}")
    public ResponseEntity<DiaryResponseDto> getDiary(@PathVariable Integer writerId, @PathVariable Integer diaryId) {
        return new ResponseEntity<>(diaryService.getDiary(writerId,diaryId), HttpStatus.OK);
    }
    //일정 수정 컨트롤러
    @PutMapping("/{writerId}/diaries/{diaryId}")
    public ResponseEntity<DiaryResponseDto> modifyDiary(@PathVariable Integer writerId, @PathVariable Integer diaryId, @RequestBody @Valid DiarySaveRequestDto dto) {
        return new ResponseEntity<>(diaryService.modifyDiary(writerId,diaryId,dto), HttpStatus.OK);
    }
    //일정 삭제 컨트롤러
    @DeleteMapping("/{writerId}/diaries/{diaryId}")
    public ResponseEntity<String> deleteDiary(@PathVariable Integer writerId, @PathVariable Integer diaryId,@RequestBody @Valid DiaryDeleteRequestDto dto) {
        diaryService.deleteDiary(writerId,diaryId,dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //페이지와 페이지 크기로 조회 컨트롤러
    @GetMapping("/{writerId}/diaries/page")
    public ResponseEntity<List<DiaryResponseDto>> getPageDiary(@PathVariable Integer writerId, @ModelAttribute @Valid DiaryFindPageRequestDto dto ) {
        return new ResponseEntity<>(diaryService.getPageDiary(writerId,dto), HttpStatus.OK);
    }
}
