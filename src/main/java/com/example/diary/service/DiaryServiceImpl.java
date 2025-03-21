package com.example.diary.service;
import com.example.diary.dto.*;
import com.example.diary.repo.DiaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepo;
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
            if(diaryRepo.modifyDiary(writerId,diaryId,dto) == 1) return new ResponseDataDto(dto.getName(),dto.getPlan());
            else throw new EmptyResultDataAccessException(1);
        }
        else throw new BadCredentialsException("패스워드가 올바르지 않습니다.");
    }
    @Override
    public void deleteDiary(Integer writerId, Integer diaryId, RequestDeleteDto dto) {
        String hashPassword=diaryRepo.authPassword(writerId,diaryId);  //해쉬값을 읽어옵니다 만약 ID로 조회되지 않으면 EmptyResultDataAccessException 예외를 throw 합니다
        String rawPassword=dto.getPassword();
        if(passwordEncoder.matches(rawPassword,hashPassword)) {
            if(diaryRepo.deleteDiary(writerId,diaryId,dto) == 1) return; //pk와 fk 를 조건으로 삭제하기 때문에 정상적인 삭제가 되었다면 삭제된 행의 갯수 1을 반환합니다
            else throw new EmptyResultDataAccessException(1);  //만약 1이 아닌 0이 반환 될 경우 ID를 잘못입력한 것이기 때문에 예외를 throw 합니다
        }
        else throw new BadCredentialsException("패스워드가 올바르지 않습니다.");
    }
    @Override
    public List<ResponseDataDto> getPageDiary(Integer writerId, RequestFindPageDto dto) {
        return diaryRepo.getPageDiary(writerId,dto);
    }
}
