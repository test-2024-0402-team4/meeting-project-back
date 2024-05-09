package com.meeteam4.meeting.entity;

import com.meeteam4.meeting.dto.DeclareStudyBoardCommentRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyCommentDeclare {
    private int declareId;
    private int userId;
    private int commentId;
    private String theme;
    private String content;
    private LocalDateTime createDate;

    private StudyComment studyComment;
    private StudyBoard studyBoard;

    public DeclareStudyBoardCommentRespDto toDeclareStudyBoardCommentRespDto() {
        return DeclareStudyBoardCommentRespDto.builder()
                .userId(userId)
                .theme(theme)
                .content(content)
                .createDate(createDate)
                .build();
    }
}
