package com.example.diary.repo;
import com.example.diary.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class JdbcTemplateDiaryRepository implements DiaryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public ResponseCreateDto createDiary(Integer writerId,RequestCreateDto dto) {

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.withTableName("diary").usingGeneratedKeyColumns("diaryId")
        .usingColumns("name","plan","password","writerId");

        Map<String,Object> params = new HashMap<>();
        params.put("name",dto.getName());
        params.put("plan",dto.getPlan());
        params.put("password",dto.getPassword());   //DB에 저장할 때는 비밀번호를 해쉬 값으로 저장합니다
        params.put("writerId",writerId);
        Number diaryId = insert.executeAndReturnKey(new MapSqlParameterSource(params));

        return new ResponseCreateDto(dto.getName(),dto.getPlan(),diaryId.intValue());
    }
    @Override
    public List<ResponseDataDto> getAllDiary(Integer writerId,RequestFindAllDto dto) {

        return jdbcTemplate.query("select name,plan from diary where writerId = ? AND name = ? AND DATE(updatedAt) = ?  order by updatedAt desc",new Object[] {writerId,dto.getName(),dto.getUpdatedAt()},
                                 (rs,num) -> new ResponseDataDto(rs.getString("name"),rs.getString("plan")));
        //해당 쿼리는 작성자 ID 와 이름이 동시에 만족한 행을 전부 읽어옵니다.
    }
    @Override
    public ResponseDataDto getDiary(Integer writerId,Integer diaryId) {
        return jdbcTemplate.queryForObject("select name,plan from diary where writerId = ? AND diaryId = ?", new Object[] {writerId,diaryId},
                                          (rs,num) -> new ResponseDataDto(rs.getString("name"),rs.getString("plan")));
    }
    @Override
    public int modifyDiary(Integer writerId, Integer diaryId, RequestModifyDto dto) {

        return jdbcTemplate.update("update diary set name = ?, plan = ? where writerId = ? and diaryId = ?",dto.getName(),dto.getPlan(),writerId,diaryId);
    }
    //해당 메서드는 해쉬된 비밀번호를 조회해서 리턴하는 메서드입니다. 비밀번호값 검증에서 이용됩니다.
    @Override
    public String authPassword(Integer writerId, Integer diaryId) {
        return jdbcTemplate.queryForObject("select password from diary where writerId = ? and diaryId = ?", new Object[] {writerId,diaryId},String.class);
    }

}
