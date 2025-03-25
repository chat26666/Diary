package com.example.diary.controller;
import com.example.diary.dto.*;
import com.example.diary.service.DiaryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/writers")
@AllArgsConstructor
@Validated
public class DiaryController {

    private final DiaryService diaryService;

    //일정 등록
    @PostMapping("/{writerId}/diaries")
    public ResponseEntity<DiaryResponseDto> createDiary(
            @PathVariable
            @Min(value = 1, message = "Writer ID 최소 입력값은 1 이상이어야 합니다.") Integer writerId,
            @RequestBody
            @Validated({Default.class,CreateGroup.class}) DiarySaveRequestDto dto
    ) {
        return new ResponseEntity<>(
                diaryService.createDiary(writerId, dto),
                HttpStatus.CREATED
        );
    }
    //전체 조회
    @GetMapping("/{writerId}/diaries")
    public ResponseEntity<List<DiaryResponseDto>> getAllDiary(
            @PathVariable
            @Min(value = 1, message = "Writer ID 최소 입력값은 1 이상이어야 합니다.") Integer writerId,
            @ModelAttribute
            @Valid DiaryFindAllRequestDto dto
    ) {
        return new ResponseEntity<>(
                diaryService.getAllDiary(writerId, dto),
                HttpStatus.OK
        );
    }
    //단건 조회
    @GetMapping("/{writerId}/diaries/{diaryId}")
    public ResponseEntity<DiaryResponseDto> getDiary(
            @PathVariable
            @Min(value = 1, message = "Writer ID 최소 입력값은 1 이상이어야 합니다.") Integer writerId,
            @PathVariable
            @Min(value = 1, message = "Diary ID 최소 입력값은 1 이상이어야 합니다.") Integer diaryId
    ) {
        return new ResponseEntity<>(
                diaryService.getDiary(writerId, diaryId),
                HttpStatus.OK
        );
    }
    //일정 수정
    @PutMapping("/{writerId}/diaries/{diaryId}")
    public ResponseEntity<String> modifyDiary(
            @PathVariable
            @Min(value = 1, message = "Writer ID 최소 입력값은 1 이상이어야 합니다.") Integer writerId,
            @PathVariable
            @Min(value = 1, message = "Diary ID 최소 입력값은 1 이상이어야 합니다.") Integer diaryId,
            @RequestBody
            @Valid DiarySaveRequestDto dto
    ) {
        diaryService.modifyDiary(writerId, diaryId, dto);
        return new ResponseEntity<>("Content modified", HttpStatus.OK);
    }
    //일정 삭제
    @DeleteMapping("/{writerId}/diaries/{diaryId}")
    public ResponseEntity<String> deleteDiary(
            @PathVariable
            @Min(value = 1, message = "Writer ID 최소 입력값은 1 이상이어야 합니다.") Integer writerId,
            @PathVariable
            @Min(value = 1, message = "Diary ID 최소 입력값은 1 이상이어야 합니다.") Integer diaryId,
            @RequestBody
            @Valid DiaryDeleteRequestDto dto
    ) {
        diaryService.deleteDiary(writerId, diaryId, dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //일정 페이지네이션
    @GetMapping("/{writerId}/diaries/page")
    public ResponseEntity<List<DiaryResponseDto>> getPageDiary(
            @PathVariable
            @Min(value = 1, message = "Writer ID 최소 입력값은 1 이상이어야 합니다.") Integer writerId,
            @ModelAttribute @Valid DiaryFindPageRequestDto dto
    ) {
        return new ResponseEntity<>(
                diaryService.getPageDiary(writerId, dto),
                HttpStatus.OK
        );
    }
}
