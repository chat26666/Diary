package com.example.diary.service;
import com.example.diary.dto.RequestCreateDto;
import com.example.diary.dto.RequestCreateUserDto;
import com.example.diary.dto.ResponseCreateDto;
import com.example.diary.repo.DiaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private DiaryRepository diaryRepo;

    public ResponseCreateDto createDiary(RequestCreateDto dto) {
        return diaryRepo.createDiary(dto);
    }
}
