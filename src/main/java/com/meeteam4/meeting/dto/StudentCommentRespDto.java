package com.meeteam4.meeting.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StudentCommentRespDto {
    private int studentCommentId;
    private int studentBoardId;
    private int studentUserId;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
