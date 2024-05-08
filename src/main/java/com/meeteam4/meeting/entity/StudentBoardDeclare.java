package com.meeteam4.meeting.entity;

import com.meeteam4.meeting.dto.DeclareStudentBoardRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentBoardDeclare {
    private int declareId;
    private int userId;
    private int boardId;
    private String theme;
    private String content;
    private LocalDateTime createDate;

    private StudentBoard studentBoard;

    public DeclareStudentBoardRespDto toDeclareStudentBoardRespDto() {
        return DeclareStudentBoardRespDto.builder()
                .boardId(boardId)
                .userId(userId)
                .theme(theme)
                .declareContent(content)
                .createDate(createDate)
                .build();
    }
}
