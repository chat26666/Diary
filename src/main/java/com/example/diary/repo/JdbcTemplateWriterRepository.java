package com.example.diary.repo;
import com.example.diary.dto.WriterResponseDto;
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
    public WriterResponseDto createUser(Writer writer) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.withTableName("writer").usingGeneratedKeyColumns("writerId")
                                      .usingColumns("name","email");
        Map<String,Object> params = new HashMap<>();
        params.put("name",writer.getName());
        params.put("email",writer.getEmail());

        Number writerId = insert.executeAndReturnKey(new MapSqlParameterSource(params));

        return new WriterResponseDto(writerId.intValue());

        //유저 등록 메서드입니다
    }
    @Override
    public int deleteUser(Writer writer) {
        return jdbcTemplate.update(
                "DELETE FROM writer WHERE name = ? and email = ? and writerId = ?",
                writer.getName(),
                writer.getEmail(),
                writer.getWriterId()
        );

        //유저 삭제 메서드입니다
        //삭제시 CASCADE 옵션으로 작성한 일정들이 전부 삭제됩니다
    }
}
