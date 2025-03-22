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

    //사용자 등록 컨트롤러입니다
    @PostMapping
    public ResponseEntity<WriterResponseDto> createUser(@RequestBody @Valid WriterRequestDto dto) {
        return new ResponseEntity<>(writerService.createUser(dto),HttpStatus.CREATED);
    }
    //사용자 삭제 컨트롤러입니다
    //패스워드가 테이블에 없기때문에 ID,이메일,이름을 검증받습니다
    //사실 좋은 인증방식도, 굳이 필요하지도 않은 기능이긴한데 테스트하면서 테이블들이 난잡해져서 제가 삭제하려고 임의로 구현한 기능입니다
    //테이블 생성시 CASCADE를 추가해놔서 작성자를 삭제하면 자동으로 일정도 같이 삭제됩니다
    @DeleteMapping("/{writerId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer writerId,@RequestBody @Valid WriterRequestDto dto) {
        writerService.deleteUser(writerId,dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
