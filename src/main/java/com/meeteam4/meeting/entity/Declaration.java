package com.meeteam4.meeting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Declaration {
    private int declareId;
    private int userId;
    private int studentBoardId;
    private int studentCommentId;
    private int teacherBoardId;
    private int teacherCommentId;
    private int studyBoardId;
    private int studyCommentId;
    private String theme;
    private String content;
    private LocalDateTime createDate;
}
