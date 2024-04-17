package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.StudyBoard;
import lombok.Data;

@Data
public class StudyBoardWriteReqDto {
    private int studyBoardId;
    private int userId;
    private String title;
    private String content;
    private int viewCount;

    public StudyBoard toEntity(){
        return StudyBoard.builder()
                .studyBoardId(studyBoardId)
                .userId(userId)
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .build();
    }
}
