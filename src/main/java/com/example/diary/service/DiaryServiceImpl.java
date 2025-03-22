package com.example.diary.service;
import com.example.diary.dto.*;
import com.example.diary.entity.Diary;
import com.example.diary.repo.DiaryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public void verifyDiaryPassword(Diary diary) {
        String hashPassword=diaryRepo.authPassword(diary);
        String rawPassword=diary.getPassword();                                  //만약 authPassword 메서드로 단 한건도 조회되지 않을시 자동으로 예외가 던져집니다
        if(passwordEncoder.matches(rawPassword,hashPassword)) return;            //Dto 로 전달된 비밀번호와 일치하지 않아도 예외가 던져집니다
        else throw new BadCredentialsException("패스워드가 올바르지 않습니다.");
    }
    @Override
    public DiaryCreateResponseDto createDiary(Integer writerId, DiarySaveRequestDto dto) {
        Diary diary = modelMapper.map(dto, Diary.class)
                                 .setWriterId(writerId)
                                 .setPassword(passwordEncoder.encode(dto.getPassword()));
        return diaryRepo.createDiary(diary);
    }
    @Override
    public List<DiaryResponseDto> getAllDiary(Integer writerId, DiaryFindAllRequestDto dto) {
        Diary diary = modelMapper.map(dto, Diary.class)
                                 .setWriterId(writerId);
        return diaryRepo.getAllDiary(diary);
    }
    @Override
    public DiaryResponseDto getDiary(Integer writerId, Integer DiaryId) {
        Diary diary = new Diary().setWriterId(writerId)
                                 .setDiaryId(DiaryId);
        return diaryRepo.getDiary(diary);
    }
    @Override
    public DiaryResponseDto modifyDiary(Integer writerId, Integer diaryId, DiarySaveRequestDto dto){
        Diary diary = modelMapper.map(dto, Diary.class)
                                 .setWriterId(writerId)
                                 .setDiaryId(diaryId);
        verifyDiaryPassword(diary);                                              //if문 분기 필요없이 인증실패시 내부에서 자동으로 예외가 던져집니다
        diaryRepo.modifyDiary(diary);                                            //따라서 예외가 던져지면 modify는 실행되지 않습니다
        return new DiaryResponseDto(dto.getName(),dto.getPlan());
    }
    @Override
    public void deleteDiary(Integer writerId, Integer diaryId, DiaryDeleteRequestDto dto) {
        Diary diary = modelMapper.map(dto, Diary.class)
                                 .setWriterId(writerId)
                                 .setDiaryId(diaryId);
        verifyDiaryPassword(diary);                                              //if문 분기 필요없이 인증실패시 내부에서 자동으로 예외가 던져집니다
        diaryRepo.deleteDiary(diary);                                            //따라서 예외가 던져지면 delete는 실행되지 않습니다
    }
    @Override
    public List<DiaryResponseDto> getPageDiary(Integer writerId, DiaryFindPageRequestDto dto) {
        int page = (dto.getPage()-1) * dto.getSize();
        int size = dto.getSize();
        return diaryRepo.getPageDiary(writerId,dto,page,size);
        //페이지와 사이즈를 계산해서 쿼리 인자로 넘겨줍니다
    }
}
