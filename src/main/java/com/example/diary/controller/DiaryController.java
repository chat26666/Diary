package com.example.diary.controller;
import com.example.diary.dto.RequestCreateDto;
import com.example.diary.dto.ResponseCreateDto;
import com.example.diary.service.DiaryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/writers")
@AllArgsConstructor
public class DiaryController {


    private final DiaryService diaryService;

    @PostMapping("/{writerId}/diaries")
    public ResponseEntity<ResponseCreateDto> createDiary(@PathVariable Integer writerId, @RequestBody RequestCreateDto dto){

        return new ResponseEntity<>(diaryService.createDiary(writerId,dto), HttpStatus.CREATED);
    }
}
