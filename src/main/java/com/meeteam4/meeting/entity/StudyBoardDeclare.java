package com.meeteam4.meeting.entity;

import com.meeteam4.meeting.dto.DeclareStudyBoardRespDto;
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
public class StudyBoardDeclare {
    private int declareId;
    private int userId;
    private int boardId;
    private String theme;
    private String content;
    private LocalDateTime createDate;

    private StudyBoard studyBoard;

    public DeclareStudyBoardRespDto toDeclareStudyBoardRespDto() {
        return DeclareStudyBoardRespDto.builder()
                .StudyBoardId(boardId)
                .userId(userId)
                .title(studyBoard.getTitle())
                .content(studyBoard.getContent())
                .theme(theme)
                .declareContent(content)
                .createDate(createDate)
                .build();
    }
}
