package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.Board;
import lombok.Data;

@Data
public class BoardWriteReqDto {

    private int studentBoardId;
    private int studentId;
    private String title;
    private String content;
    private String theme;
    private int viewCount;

    public Board toEntity(){
        return Board.builder()
                .studentId(studentId)
                .title(title)
                .content(content)
                .theme(theme)
                .viewCount(viewCount)
                .build();
    }
}
