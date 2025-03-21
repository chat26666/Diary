package com.example.diary.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
public class Diary {
    private Integer diaryId;
    private Integer writerId;       //일정생성을 위해서는 먼저 사용자 등록부터 진행해야합니다. 때문에 멤버변수로 writerId를 가지고 있습니다
    private String name;            //계층간 데이터 전송을 전부 dto 로 해결한 탓에 사용되지는 않았습니다
    private String plan;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
