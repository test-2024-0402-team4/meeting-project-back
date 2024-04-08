package com.meeteam4.meeting.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StudentCommentPageRespDto {
    private int studentBoardId;
    private int studentId;
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime createDate;
    
}
