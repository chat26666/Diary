package com.example.diary.repo;
import com.example.diary.dto.RequestCreateDto;
import com.example.diary.dto.ResponseCreateDto;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class JdbcTemplateDiaryRepository implements DiaryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public ResponseCreateDto createDiary(RequestCreateDto dto) {

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.withTableName("diary").usingGeneratedKeyColumns("diaryId")
        .usingColumns("name","plan","password","writerId");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedpassword = passwordEncoder.encode(dto.getPassword());
        //DB에 저장할 때는 비밀번호를 해쉬 값으로 저장합니다

        Map<String,Object> params = new HashMap<>();
        params.put("name",dto.getName());
        params.put("plan",dto.getPlan());
        params.put("password",hashedpassword);
        params.put("writerId",dto.getWriterId());
        Number diaryId = insert.executeAndReturnKey(new MapSqlParameterSource(params));

        return new ResponseCreateDto(dto.getName(),dto.getPlan(),diaryId.intValue());
    }

}
