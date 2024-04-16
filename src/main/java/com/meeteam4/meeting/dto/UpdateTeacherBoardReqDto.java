package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.TeacherBoard;
import lombok.Data;

@Data
public class UpdateTeacherBoardReqDto {
    private int teacherBoardId;
    private int teacherId;
    private String title;
    private String content;
    private int viewCount;

    public TeacherBoard toEntity(){
        return TeacherBoard.builder()
                .teacherBoardId(teacherBoardId)
                .title(title)
                .content(content)
                .build();
    }
}
