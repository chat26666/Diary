package com.example.diary.repo;
import com.example.diary.dto.WriterCreateResponseDto;
import com.example.diary.entity.Writer;
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
    public WriterCreateResponseDto createUser(Writer writer) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.withTableName("writer").usingGeneratedKeyColumns("writerId")
                                      .usingColumns("name","email");
        Map<String,Object> params = new HashMap<>();
        params.put("name",writer.getName());
        params.put("email",writer.getEmail());

        Number writerId = insert.executeAndReturnKey(new MapSqlParameterSource(params));

        return new WriterCreateResponseDto(writerId.intValue());
    }
}
