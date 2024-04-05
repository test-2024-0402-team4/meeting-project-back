package com.meeteam4.meeting.entity;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class studyBoard {
    private int studyBoardId;
    private int studentId;
    private int teacherId;
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
