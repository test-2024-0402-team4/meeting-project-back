package com.meeteam4.meeting.entity;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TeacherBoard {
    private int teacherBoardId;
    private int teacherId;
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
