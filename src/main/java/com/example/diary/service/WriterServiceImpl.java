package com.example.diary.service;
import com.example.diary.dto.WriterRequestDto;
import com.example.diary.dto.WriterResponseDto;
import com.example.diary.entity.Writer;
import com.example.diary.repo.WriterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WriterServiceImpl implements WriterService {

    private final WriterRepository writerRepo;
    private final ModelMapper modelMapper;

    @Override
    public WriterResponseDto createUser(WriterRequestDto dto){
        Writer writer = modelMapper.map(dto, Writer.class);
        return writerRepo.createUser(writer);
    }
    public void deleteUser(Integer writerId,WriterRequestDto dto) {
        Writer writer = modelMapper.map(dto, Writer.class)
                                   .setWriterId(writerId);
        if(writerRepo.deleteUser(writer) == 1) return;
        else throw new DataIntegrityViolationException("");
        //JDBC템플릿 update 메소드는 값이 조회되지않아도 예외발생이 되지않기 때문에 수동으로 throw 해줍니다
    }
}
