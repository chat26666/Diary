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
                                 .setDiaryId(diaryId)
                                 .setWriterId(writerId);
        String hashPassword=diaryRepo.authPassword(diary);
        String rawPassword=diary.getPassword();
        if(passwordEncoder.matches(rawPassword,hashPassword)) {   //dto 로 전달된 비밀번호와 해쉬화된 비밀번호를 비교하고 일치해야 수정이 가능합니다
            diary.setPassword(hashPassword);
            diaryRepo.modifyDiary(diary);
            return new DiaryResponseDto(diary.getName(),diary.getPlan());
        }
        else throw new BadCredentialsException("패스워드가 올바르지 않습니다.");
    }
    @Override
    public void deleteDiary(Integer writerId, Integer diaryId, DiaryDeleteRequestDto dto) {
        Diary diary = modelMapper.map(dto, Diary.class)
                                 .setDiaryId(diaryId)
                                 .setWriterId(writerId);
        String hashPassword=diaryRepo.authPassword(diary);
        String rawPassword=diary.getPassword();
        if(passwordEncoder.matches(rawPassword,hashPassword)) {
            diary.setPassword(hashPassword);
            diaryRepo.deleteDiary(diary);
        }
        else throw new BadCredentialsException("패스워드가 올바르지 않습니다.");
    }
    @Override
    public List<DiaryResponseDto> getPageDiary(Integer writerId, DiaryFindPageRequestDto dto) {
        return diaryRepo.getPageDiary(writerId,dto);
    }
}
