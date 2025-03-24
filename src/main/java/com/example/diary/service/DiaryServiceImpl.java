package com.example.diary.service;
import com.example.diary.dto.*;
import com.example.diary.entity.Diary;
import com.example.diary.repo.DiaryRepository;
import com.example.diary.repo.DiaryJoinViewRepository;
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
    private final DiaryJoinViewRepository joinRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public void verifyDiaryPassword(Diary diary) {
        String hashPassword=diaryRepo.authPassword(diary);
        String rawPassword=diary.getPassword();
        if(passwordEncoder.matches(rawPassword,hashPassword)) return;
        else throw new BadCredentialsException("패스워드가 올바르지 않습니다.");

        //만약 authPassword 메서드로 단 한건도 조회되지 않을시 자동으로 예외가 던져집니다
        //Dto 로 전달된 비밀번호와 일치하지 않아도 예외가 던져집니다
    }
    @Override
    public DiaryResponseDto createDiary(Integer writerId, DiarySaveRequestDto dto) {
        Diary diary = modelMapper.map(dto, Diary.class)
                                 .setWriterId(writerId)
                                 .setPassword(passwordEncoder.encode(dto.getPassword()));
        return diaryRepo.createDiary(diary);

        //일정 생성 서비스 메서드입니다 Dto 로 받은 값을 Entity 에 맵핑하며 이 과정에서 비밀번호 인코딩이 이뤄집니다
        //맵핑된 엔티티는 Repository 가 받아서 Insert 를 진행합니다
    }
    @Override
    public List<DiaryResponseDto> getAllDiary(Integer writerId, DiaryFindAllRequestDto dto) {
        Diary diary = modelMapper.map(dto, Diary.class)
                                 .setWriterId(writerId);
        return joinRepo.getAllDiary(diary);

        //결과를 select 할때 조인을 사용하기 때문에 joinRepo 에서 결과를 긁어옵니다
    }
    @Override
    public DiaryResponseDto getDiary(Integer writerId, Integer DiaryId) {
        Diary diary = new Diary().setWriterId(writerId)
                                 .setDiaryId(DiaryId);
        return joinRepo.getDiary(diary);

        //결과를 select 할때 조인을 사용하기 때문에 joinRepo 에서 결과를 긁어옵니다
    }
    @Override
    public void modifyDiary(Integer writerId, Integer diaryId, DiarySaveRequestDto dto){
        Diary diary = modelMapper.map(dto, Diary.class)
                                 .setWriterId(writerId)
                                 .setDiaryId(diaryId);
        verifyDiaryPassword(diary);
        diaryRepo.modifyDiary(diary);

        //if문 분기 필요없이 인증실패시 내부에서 자동으로 예외가 던져집니다(throws)
        //따라서 예외가 던져지면 modify 는 실행되지 않습니다
    }
    @Override
    public void deleteDiary(Integer writerId, Integer diaryId, DiaryDeleteRequestDto dto) {
        Diary diary = modelMapper.map(dto, Diary.class)
                                 .setWriterId(writerId)
                                 .setDiaryId(diaryId);
        verifyDiaryPassword(diary);
        diaryRepo.deleteDiary(diary);

        //if문 분기 필요없이 인증실패시 내부에서 자동으로 예외가 던져집니다(throws)
        //따라서 예외가 던져지면 delete 는 실행되지 않습니다
    }
    @Override
    public List<DiaryResponseDto> getPageDiary(Integer writerId, DiaryFindPageRequestDto dto) {
        int page = (dto.getPage()-1) * dto.getSize();
        int size = dto.getSize();
        return joinRepo.getPageDiary(writerId,dto,page,size);

        //결과를 select 할때 조인을 사용하기 때문에 joinRepo 에서 결과를 긁어옵니다
        //페이지와 사이즈를 계산해서 쿼리 인자로 넘겨줍니다
    }
}
