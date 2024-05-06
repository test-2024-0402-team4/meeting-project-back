package com.meeteam4.meeting.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StudyCommentRespDto {
    private int studyCommentId;
    private int studyBoardId;
    private int userId;
    private String nickname;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
