package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.TeacherBoard;
import lombok.Data;

@Data
public class TeacherBoardWriteReqDto {
    private int teacherBoardId;
    private int teacherId;
    private String nickname;
    private String title;
    private String content;
    private int viewCount;

    public TeacherBoard toEntity(){
        return TeacherBoard.builder()
                .teacherId(teacherId)
                .nickname(nickname)
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .build();
    }
}
