package com.example.diary.repo;
import com.example.diary.dto.WriterCreateResponseDto;
import com.example.diary.entity.Writer;

public interface WriterRepository {
    WriterCreateResponseDto createUser(Writer writer);
}
