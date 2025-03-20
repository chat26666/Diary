package com.example.diary.service;
import com.example.diary.dto.*;
import com.example.diary.repo.DiaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private DiaryRepository diaryRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public ResponseCreateDto createDiary(Integer writerId,RequestCreateDto dto) {

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        //생각해보니 해쉬작업부분은 서비스 레이어 있는것이 더 옳다고 생각이들어 옮겼습니다. repo -> service
        return diaryRepo.createDiary(writerId,dto);
    }
    @Override
    public List<ResponseDataDto> getAllDiary(Integer writerId, RequestFindAllDto dto) {
        return diaryRepo.getAllDiary(writerId,dto);
    }
    @Override
    public ResponseDataDto getDiary(Integer writerId, Integer DiaryId) {
        return diaryRepo.getDiary(writerId,DiaryId);
    }
    @Override
    public ResponseDataDto modifyDiary(Integer writerId, Integer diaryId, RequestModifyDto dto){
        String hashPassword=diaryRepo.authPassword(writerId,diaryId);
        String rawPassword=dto.getPassword();
        if(passwordEncoder.matches(rawPassword,hashPassword)) {   //dto 로 전달된 비밀번호와 해쉬화된 비밀번호를 비교하고 일치해야 수정이 가능합니다
            diaryRepo.modifyDiary(writerId,diaryId,dto);
            return new ResponseDataDto(dto.getName(),dto.getPlan());
        }
        else throw new BadCredentialsException("패스워드가 올바르지 않습니다.");
    }
    @Override
    public void deleteDiary(Integer writerId, Integer diaryId, RequestDeleteDto dto) {
        String hashPassword=diaryRepo.authPassword(writerId,diaryId);
        String rawPassword=dto.getPassword();
        if(passwordEncoder.matches(rawPassword,hashPassword)) {
            if(diaryRepo.deleteDiary(writerId,diaryId,dto) == 1) return; //pk와 fk 를 조건으로 삭제하기 때문에 정상적인 삭제가 되었다면 삭제된 행의 갯수 1을 반환합니다
            else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다.");
        }
        else throw new BadCredentialsException("패스워드가 올바르지 않습니다.");
    }
    @Override
    public List<ResponseDataDto> getPageDiary(Integer writerId, RequestFindPageDto dto) {
        return diaryRepo.getPageDiary(writerId,dto);
    }
}
