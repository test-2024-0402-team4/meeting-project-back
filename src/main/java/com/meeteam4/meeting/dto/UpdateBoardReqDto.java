package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.StudentBoard;
import lombok.Data;

@Data
public class UpdateBoardReqDto {
    private int studentBoardId;
    private int studentId;
    private String title;
    private String content;
    private String theme;
    private int viewCount;

    public StudentBoard toEntity(){
        return StudentBoard.builder()
                .studentBoardId(studentBoardId)
                .title(title)
                .content(content)
                .theme(theme)
                .build();
    }
}
