package com.meeteam4.meeting.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class teacherComment {

    private int teacherCommentId;
    private int teacherBoardId;
    private int teacherId;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
