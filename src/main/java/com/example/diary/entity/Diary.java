package com.example.diary.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Diary {
    private Integer diaryId;
    private Integer writerId;       //일정생성을 위해서는 먼저 사용자 등록부터 진행해야합니다. 때문에 멤버변수로 writerId를 가지고 있습니다
    private String password;
    private String title;
    private String plan;
    private LocalDate createdAt;    //최대한 테이블과 같게 멤버변수를 선언하였습니다
    private LocalDate updatedAt;
}