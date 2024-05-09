package com.meeteam4.meeting.entity;

import com.meeteam4.meeting.dto.DeclareStudentBoardRespDto;
import com.meeteam4.meeting.dto.DeclareTeacherBoardRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherBoardDeclare {
    private int declareId;
    private int userId;
    private int boardId;
    private String theme;
    private String content;
    private LocalDateTime createDate;

    private TeacherBoard teacherBoard;

    public DeclareTeacherBoardRespDto toDeclareTeacherBoardRespDto() {
        return DeclareTeacherBoardRespDto.builder()
                .boardId(boardId)
                .userId(userId)
                .title(teacherBoard.getTitle())
                .theme(theme)
                .declareContent(content)
                .createDate(createDate)
                .build();
    }
}
