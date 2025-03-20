package com.example.diary.service;
import com.example.diary.dto.RequestCreateUserDto;
import com.example.diary.dto.ResponseCreateUserDto;
import com.example.diary.repo.WriterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WriterServiceImpl implements WriterService {

    private final WriterRepository writerRepo;

    public ResponseCreateUserDto createUser(RequestCreateUserDto dto){
        return writerRepo.createUser(dto);
    }
}
