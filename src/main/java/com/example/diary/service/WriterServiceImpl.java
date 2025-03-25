package com.example.diary.service;
import com.example.diary.dto.WriterRequestDto;
import com.example.diary.dto.WriterResponseDto;
import com.example.diary.entity.Writer;
import com.example.diary.exception.UserNotFoundException;
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
    public WriterResponseDto createUser(WriterRequestDto dto){
        Writer writer = modelMapper.map(dto, Writer.class);
        return writerRepo.createUser(writer);
    }
    public void deleteUser(Integer writerId,WriterRequestDto dto) {
        Writer writer = modelMapper.map(dto, Writer.class)
                                   .setWriterId(writerId);
        if(writerRepo.deleteUser(writer) == 0) throw new UserNotFoundException("삭제하려는 유저가 조회되지 않습니다. 아이디, 이메일, 이름이 정확한지 확인해주십시오.");

        /* JdbcTemplate update 메소드는 select 한 row 없는 경우에도
           예외발생이 없기 때문에(update 된 row 갯수를 int 값으로 반환) 수동으로 정의한 예외를 throw 해줍니다
           현재 같은 경우에는 업데이트 된 row 가 0 일 경우 예외를 던집니다 */
    }
}
