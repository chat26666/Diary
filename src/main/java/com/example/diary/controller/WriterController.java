package com.example.diary.controller;
import com.example.diary.dto.RequestCreateUserDto;
import com.example.diary.dto.ResponseCreateUserDto;
import com.example.diary.service.WriterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/writers")
@AllArgsConstructor
public class WriterController {
    private final WriterService writerService;

    //사용자 등록 컨트롤러입니다
    @PostMapping
    public ResponseEntity<ResponseCreateUserDto> createUser(@RequestBody @Valid RequestCreateUserDto dto) {
        return new ResponseEntity<>(writerService.createUser(dto),HttpStatus.CREATED);
    }

}
