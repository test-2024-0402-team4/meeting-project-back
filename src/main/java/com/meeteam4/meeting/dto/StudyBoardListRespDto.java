package com.meeteam4.meeting.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StudyBoardListRespDto {
    private int studyBoardId;
    private int userId;
    private String nickname;
    private String title;
    private String content;
    private int viewCount;
    private int genderId;
    private LocalDateTime createDate;
    private int commentCount;
}
