package com.meeteam4.meeting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherComment {

    private int teacherCommentId;
    private int teacherBoardId;
    private int teacherId;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
