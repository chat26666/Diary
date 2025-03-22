package com.example.diary.repo;
import com.example.diary.dto.DiaryFindPageRequestDto;
import com.example.diary.dto.DiaryResponseDto;
import com.example.diary.entity.Diary;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@AllArgsConstructor
//해당 Repo 는 Diaryrepo 와 Writerepo 조인용도로 사용을 위해 만들어졌습니다
public class JdbcTemplateJoinRepository implements JoinRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<DiaryResponseDto> getPageDiary(Integer writerId, DiaryFindPageRequestDto dto, int page, int size) throws BadSqlGrammarException {
        return jdbcTemplate.query(
                "SELECT b.name, a.title, a.plan " +
                        "FROM diary a " +
                        "INNER JOIN writer b " +
                        "ON a.writerId = b.writerId " +
                        "WHERE a.writerId = ? " +
                        "ORDER BY a.updatedAt DESC " +
                        "LIMIT ? OFFSET ?",
                new Object[] { writerId, size, page },
                (rs, num) -> new DiaryResponseDto(
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("plan")
                )
        );

        //두개의 테이블에서 join 을 사용하여 데이터를 가져옵니다
        //단순한 페이지와 사이즈만 전달받기에 따로 엔티티로 받아서 넘기지않고 바로 dto를 넘겼습니다
        //페이징 기능을 쿼리에서 offset 과 limit 으로 처리하였습니다
        //limit 은 한꺼번에 읽을 페이지의 사이즈이고 page 번호는 쿼리스트링으로 넘겨진 값을 곱해서 시작 위치(offset)를 조정해주었습니다
        //범위를 벗어나면 빈 배열을 리턴하나 만약 page 와 size 가 음수값으로 올 경우 BadSqlGrammarException 가 throws 됩니다
    }
    @Override
    public List<DiaryResponseDto> getAllDiary(Diary diary) {
        return jdbcTemplate.query(
                "SELECT b.name, a.title,a.plan " +
                        "FROM diary a " +
                        "INNER JOIN writer b " +
                        "ON a.writerId = b.writerId " +
                        "WHERE a.writerId = ? " +
                        "AND DATE(a.updatedAt) = ? " +
                        "ORDER BY a.updatedAt DESC",
                new Object[] {
                        diary.getWriterId(),
                        diary.getUpdatedAt()
                },
                (rs, num) -> new DiaryResponseDto(
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("plan")
                )
        );

        //두개의 테이블에서 join 을 사용하여 데이터를 가져옵니다
        //해당 쿼리는 작성자 ID 그리고 날짜를 동시에 만족해야 조회됩니다
        //작성자 테이블에 이름을 등록하고 ID를 통해 일정을 관리하기 때문에 이름을 굳이 검색 조건으로 받지 않습니다
        //ID를 통해 조회하기 때문에 더 이상 동명이인하고 결과값이 겹치지않습니다
        //select 시 조건에 맞지 않아 아무런 값이 조회되지 않으면 자동으로 빈 리스트를 리턴합니다
    }
    @Override
    public DiaryResponseDto getDiary(Diary diary) throws EmptyResultDataAccessException {
        return jdbcTemplate.queryForObject(
                "SELECT b.name,a.title, a.plan " +
                        "FROM diary a " +
                        "INNER JOIN writer b " +
                        "ON a.writerId = b.writerId " +
                        "WHERE a.writerId = ? AND a.diaryId = ?",
                new Object[] { diary.getWriterId(), diary.getDiaryId() },
                (rs, num) -> new DiaryResponseDto(
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("plan")
                )
        );

        //두개의 테이블에서 join 을 사용하여 데이터를 가져옵니다
        //queryForObject는 row 가 조회되지 않을시 EmptyResultDataAccessException 예외를 발생시키며 해당 메서드는 throws 하여 해당 예외는 ExceptionHandler 에서 처리합니다
        //사용자와 일정관리 테이블을 나눴기 때문에 사용자 계정을 먼저 등록해야합니다 따라서 일정 ID 뿐만 아니라 사용자 ID도 일치해야 조회가 가능합니다(이름이 같은 사람일 경우 대비)
    }
}
