package com.meeteam4.meeting.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TeacherCommentRespDto {
    private int teacherCommentId;
    private int teacherBoardId;
    private int teacherId;
    private String nickname;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
