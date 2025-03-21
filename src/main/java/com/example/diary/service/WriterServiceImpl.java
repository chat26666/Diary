package com.example.diary.service;
import com.example.diary.dto.WriterCreateRequestDto;
import com.example.diary.dto.WriterCreateResponseDto;
import com.example.diary.entity.Writer;
import com.example.diary.repo.WriterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WriterServiceImpl implements WriterService {

    private final WriterRepository writerRepo;
    private final ModelMapper modelMapper;

    @Override
    public WriterCreateResponseDto createUser(WriterCreateRequestDto dto){
        Writer writer = modelMapper.map(dto, Writer.class);
        return writerRepo.createUser(writer);
    }
}
