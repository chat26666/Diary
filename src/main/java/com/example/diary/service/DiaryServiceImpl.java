package com.example.diary.service;
import com.example.diary.dto.*;
import com.example.diary.repo.DiaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private DiaryRepository diaryRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public ResponseCreateDto createDiary(Integer writerId,RequestCreateDto dto) {

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        //생각해보니 해쉬작업부분은 서비스 레이어 있는것이 더 옳다고 생각이들어 옮겼습니다. repo -> service
        return diaryRepo.createDiary(writerId,dto);
    }
    public List<ResponseDataDto> getAllDiary(Integer writerId, RequestFindAllDto dto) {
        return diaryRepo.getAllDiary(writerId,dto);
    }
    public ResponseDataDto getDiary(Integer writerId, Integer DiaryId) {
        return diaryRepo.getDiary(writerId,DiaryId);
    }
    public ResponseDataDto modifyDiary(Integer writerId, Integer DiaryId, RequestModifyDto dto){
        String hashPassword=diaryRepo.authPassword(writerId,DiaryId);
        String rawPassword=dto.getPassword();
        if(passwordEncoder.matches(rawPassword,hashPassword)) {
            diaryRepo.modifyDiary(writerId,DiaryId,dto);
            return new ResponseDataDto(dto.getName(),dto.getPlan());
        }
        else throw new BadCredentialsException("패스워드가 올바르지 않습니다.");
    }
}
