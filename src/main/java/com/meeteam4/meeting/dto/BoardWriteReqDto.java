package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.Board;
import lombok.Data;

@Data
public class BoardWriteReqDto {

    private int boardId;
    private int userId;
    private String title;
    private String content;
    private int viewCount;

    public Board toEntity(){
        return Board.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .build();
    }
}
