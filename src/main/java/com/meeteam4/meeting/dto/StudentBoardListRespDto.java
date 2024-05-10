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
    private int userId;
    private String nickname;
    private String title;
    private String content;
    private String theme;
    private int viewCount;
    private int genderId;
    private int commentCount;
    private LocalDateTime createDate;


}
