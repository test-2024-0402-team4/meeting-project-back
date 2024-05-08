package com.meeteam4.meeting.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DeclareStudentBoardRespDto {
    private String nickName;
    private String title;
    private String content;
    private String theme;
    private String declareContent;
    private int userId;
    private int boardId;
    private LocalDateTime createDate;

}
