package com.example.diary.controller;
import com.example.diary.dto.*;
import com.example.diary.service.DiaryService;
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

    @PostMapping("/{writerId}/diaries")
    public ResponseEntity<ResponseCreateDto> createDiary(@PathVariable Integer writerId, @RequestBody RequestCreateDto dto){

        return new ResponseEntity<>(diaryService.createDiary(writerId,dto), HttpStatus.CREATED);
    }
    @GetMapping("/{writerId}/diaries")
    public ResponseEntity<List<ResponseDataDto>> getAllDiary(@PathVariable Integer writerId, @ModelAttribute RequestFindAllDto dto) {
        return new ResponseEntity<>(diaryService.getAllDiary(writerId,dto), HttpStatus.OK);
    }
    @GetMapping("/{writerId}/diaries/{diaryId}")
    public ResponseEntity<ResponseDataDto> getDiary(@PathVariable Integer writerId, @PathVariable Integer diaryId) {
        return new ResponseEntity<>(diaryService.getDiary(writerId,diaryId), HttpStatus.OK);
    }
    @PutMapping("/{writerId}/diaries/{diaryId}")
    public ResponseEntity<ResponseDataDto> modifyDiary(@PathVariable Integer writerId, @PathVariable Integer diaryId, @RequestBody RequestModifyDto dto) {
        return new ResponseEntity<>(diaryService.modifyDiary(writerId,diaryId,dto), HttpStatus.OK);
    }
    @DeleteMapping("/{writerId}/diaries/{diaryId}")
    public ResponseEntity<String> deleteDiary(@PathVariable Integer writerId, @PathVariable Integer diaryId,@RequestBody RequestDeleteDto dto) {
        diaryService.deleteDiary(writerId,diaryId,dto);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{writerId}/diaries/page")
    public ResponseEntity<List<ResponseDataDto>> getPageDiary(@PathVariable Integer writerId, @ModelAttribute RequestFindPageDto dto ) {
        return new ResponseEntity<>(diaryService.getPageDiary(writerId,dto), HttpStatus.OK);
    }
}
