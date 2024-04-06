package com.meeteam4.meeting.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class StudyComment {
    private int studyCommentId;
    private int studyBoardId;
    private int studentId;
    private int teacherId;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
