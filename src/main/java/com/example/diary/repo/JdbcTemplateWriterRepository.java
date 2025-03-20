package com.example.diary.repo;
import com.example.diary.dto.RequestCreateUserDto;
import com.example.diary.dto.ResponseCreateUserDto;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class JdbcTemplateWriterRepository implements WriterRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public ResponseCreateUserDto createUser(RequestCreateUserDto dto) {

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.withTableName("writer").usingGeneratedKeyColumns("writerId")
                                      .usingColumns("name","email");
        //usingColums 메서드를 반드시 사용해야합니다. writer 테이블은 입력되지 않은
        //생성시간 및 수정시간을 자동으로 현재시간이 들어가게끔 테이블이 구성되어있는데 문제는 심플jdbcinsert 에서
        //컬럼을 명시하지않으면 자동으로 null값을 넣어버립니다 때문에 메소드가 없으면 시간에는 전부 null 값이 반영됩니다
        Map<String,Object> params = new HashMap<>();
        params.put("name",dto.getName());
        params.put("email",dto.getEmail());

        Number writerId = insert.executeAndReturnKey(new MapSqlParameterSource(params));

        return new ResponseCreateUserDto(writerId.intValue());
    }
}
