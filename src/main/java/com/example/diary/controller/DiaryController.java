package com.example.diary.controller;
import com.example.diary.dto.RequestCreateDto;
import com.example.diary.dto.ResponseCreateDto;
import com.example.diary.service.DiaryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diaries")
@AllArgsConstructor
public class DiaryController {


    private final DiaryService diaryService;

    @PostMapping
    public ResponseEntity<ResponseCreateDto> createDiary(@RequestBody RequestCreateDto dto){

        return new ResponseEntity<>(diaryService.createDiary(dto), HttpStatus.CREATED);
    }
}
