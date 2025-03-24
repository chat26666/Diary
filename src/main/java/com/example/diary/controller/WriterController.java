package com.example.diary.controller;
import com.example.diary.dto.WriterRequestDto;
import com.example.diary.dto.WriterResponseDto;
import com.example.diary.service.WriterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/writers")
@AllArgsConstructor
public class WriterController {
    private final WriterService writerService;

    // 사용자 등록
    @PostMapping
    public ResponseEntity<WriterResponseDto> createUser(@RequestBody @Valid WriterRequestDto dto) {
        return new ResponseEntity<>(writerService.createUser(dto),HttpStatus.CREATED);
    }
    /* 사용자 삭제
       패스워드가 Writer 테이블에 없기때문에 ID,이메일,이름 등의 다른 개인정보를 검증받습니다
       패스워드 인증이 없기 때문에 보안상 좋지는 않지만 일괄 삭제할 때 편리합니다.
       테이블 생성시 CASCADE 를 걸어놔서 작성자를 삭제하면 자동으로 일정도 전부 다같이 삭제됩니다 */
    @DeleteMapping("/{writerId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer writerId,@RequestBody @Valid WriterRequestDto dto) {
        writerService.deleteUser(writerId,dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
