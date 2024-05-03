package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.Declaration;
import lombok.Data;

@Data
public class StudyDeclareReqDto {
    private int declareId;
    private int userId;
    private int studyBoardId;
    private int studyCommentId;
    private String theme;
    private String content;

    public Declaration toEntity(){
        return Declaration.builder()
                .declareId(declareId)
                .userId(userId)
                .studyBoardId(studyBoardId)
                .theme(theme)
                .content(content)
                .build();
    }
    public Declaration studyCommentDeclare(){
        return Declaration.builder()
                .declareId(declareId)
                .userId(userId)
                .studyCommentId(studyCommentId)
                .theme(theme)
                .content(content)
                .build();
    }
}
