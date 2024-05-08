package com.meeteam4.meeting.entity;

import com.meeteam4.meeting.dto.DeclareStudyBoardCommentRespDto;
import com.meeteam4.meeting.dto.DeclareTeacherBoardCommentRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCommentDeclare {
    private int declareId;
    private int userId;
    private int commentId;
    private String theme;
    private String content;
    private LocalDateTime createDate;

    private TeacherComment teacherComment;
    private TeacherBoard teacherBoard;

    public DeclareTeacherBoardCommentRespDto toDeclareStudyBoardCommentRespDto() {
        return DeclareTeacherBoardCommentRespDto.builder()
                .userId(userId)
                .theme(theme)
                .content(content)
                .createDate(createDate)
                .build();
    }
}
