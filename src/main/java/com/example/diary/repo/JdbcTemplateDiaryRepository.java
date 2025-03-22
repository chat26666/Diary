package com.example.diary.repo;
import com.example.diary.dto.*;
import com.example.diary.entity.Diary;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class JdbcTemplateDiaryRepository implements DiaryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public DiaryCreateResponseDto createDiary(Diary diary) throws DataIntegrityViolationException {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.withTableName("diary").usingGeneratedKeyColumns("diaryId")
                                     .usingColumns("title","plan","password","writerId");

        //usingColumns 메서드를 반드시 사용해야합니다. writer 테이블은 입력되지 않은
        //생성시간 및 수정시간을 자동으로 현재시간이 들어가게끔 테이블이 구성되어있는데 문제는 SimpleJdbcInsert 에서
        //컬럼을 명시하지않으면 자동으로 null 값을 넣어버립니다 때문에 해당 메소드가 없으면 시간에는 전부 null 값이 반영됩니다

        Map<String,Object> params = new HashMap<>();
        params.put("title",diary.getTitle());
        params.put("plan",diary.getPlan());
        params.put("password",diary.getPassword());
        params.put("writerId",diary.getWriterId());

        Number diaryId = insert.executeAndReturnKey(new MapSqlParameterSource(params));
        return new DiaryCreateResponseDto(diary.getTitle(),diary.getPlan(),diaryId.intValue());

        //DB에 저장할 때는 비밀번호를 해쉬 값으로 저장합니다
        //추가적으로 코드 상에서는 구현되어있지 않지만 일정이 추가되면 작성자 테이블의 생성 시간도 자동으로 변경되게끔 처리되어있습니다
        //DB 트리거의 기능을 활용하여 일정 테이블이 INSERT 되는 시점에 작성자 테이블의 최근 수정시간이 트리거로 인해 자동으로 변경됩니다
        //해당 기능을 통해 작성자 테이블(writer)에서 사용자가 마지막으로 언제 글을 썼는지 혹은 수정했는지 확인이 가능합니다
        //my_diary.sql 파일 참조부탁드립니다
    }

    @Override
    public void modifyDiary(Diary diary) {
        jdbcTemplate.update(
                "UPDATE diary SET title = ?, plan = ? WHERE writerId = ? AND diaryId = ?",
                diary.getTitle(),
                diary.getPlan(),
                diary.getWriterId(),
                diary.getDiaryId()
        );

        //문제의 요구사항은 작성자의 이름과 일정내용을 변경하는 것이지만 현재는 작성자/일정 테이블로 나눠졌고
        //상식적으로 생각해보았을 때, 일정 게시글의 내용과 제목을 변경하는 것이 좀 더 맞는 방향성이 아닌가 싶어 title 을 변경처리하였습니다
        //작성자 이름은 ID 및 개인정보를 저장하는 writerRepo 에서만 변경이 가능합니다

        //추가적으로 코드 상에서는 구현되어있지 않지만 일정이 변경이되면 작성자 테이블의 수정시간도 자동으로 변경되게끔 처리되어있습니다
        //DB 트리거의 기능을 활용하여 일정 테이블이 UPDATE 되는 시점에 작성자 테이블의 수정시간이 트리거로 인해 똑같이 변경됩니다
        //해당 기능을 통해 작성자 테이블(writer)에서 사용자가 마지막으로 언제 글을 썼는지 혹은 수정했는지 확인이 가능합니다
        //my_diary.sql 파일 참조부탁드립니다
    }
    @Override
    public String authPassword(Diary diary) throws EmptyResultDataAccessException {
        return jdbcTemplate.queryForObject(
                "SELECT password FROM diary WHERE writerId = ? AND diaryId = ?",
                new Object[] { diary.getWriterId(), diary.getDiaryId() },
                String.class
        );

        //해당 메서드는 해쉬된 비밀번호를 조회해서 리턴하는 메서드입니다. 비밀번호값 검증에서 이용됩니다
        //조회가 되지않으면 EmptyResultDataAccessException 예외를 던집니다
    }
    @Override
    public void deleteDiary(Diary diary) {
        jdbcTemplate.update(
                "DELETE FROM diary WHERE writerId = ? AND diaryId = ?",
                diary.getWriterId(),
                diary.getDiaryId()
        );
    }
}
