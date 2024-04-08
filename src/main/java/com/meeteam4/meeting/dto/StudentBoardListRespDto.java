package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.Poster;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StudentBoardListRespDto {
    private int studentBoardId;
    private int studentId;
    private String title;
    private String content;
    private String theme;
    private int viewCount;
    private LocalDateTime createDate;


}
