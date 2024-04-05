package com.meeteam4.meeting.entity;

import com.meeteam4.meeting.dto.StudentBoardListRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentBoard {
    private int studentBoardId;
    private int studentId;
    private String title;
    private String content;
    private String theme;
    private int viewCount;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public StudentBoardListRespDto toStudentBoardListRespDto(){
        return StudentBoardListRespDto.builder()
                .studentBoardId(studentBoardId)
                .studentId(studentId)
                .title(title)
                .content(content)
                .theme(theme)
                .viewCount(viewCount)
                .createDate(createDate)
                .build();
    }
}
