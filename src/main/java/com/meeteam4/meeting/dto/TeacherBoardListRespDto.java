package com.meeteam4.meeting.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TeacherBoardListRespDto {
    private int teacherBoardId;
    private int teacherId;
    private int userId;
    private String nickname;
    private String title;
    private String content;
    private int viewCount;
    private int genderId;
    private LocalDateTime createDate;
}
