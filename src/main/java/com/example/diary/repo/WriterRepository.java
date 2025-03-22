package com.example.diary.repo;
import com.example.diary.dto.WriterResponseDto;
import com.example.diary.entity.Writer;

public interface WriterRepository {

    WriterResponseDto createUser(Writer writer);
    int deleteUser(Writer writer);
}
