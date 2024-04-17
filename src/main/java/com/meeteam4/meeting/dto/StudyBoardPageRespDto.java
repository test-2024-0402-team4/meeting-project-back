package com.meeteam4.meeting.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StudyBoardPageRespDto {
    private int studyBoardId;
    private int userId;
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
