package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.Declaration;
import com.meeteam4.meeting.entity.StudentComment;
import lombok.Data;

@Data
public class DeclareReqDto {
    private int declareId;
    private int userId;
    private int studentBoardId;
    private int studentCommentId;
    private String theme;
    private String content;


    public Declaration toEntity(){
        return Declaration.builder()
                .declareId(declareId)
                .userId(userId)
                .studentBoardId(studentBoardId)
                .theme(theme)
                .content(content)
                .build();
    }
    public Declaration studentCommentDeclare(){
        return Declaration.builder()
                .declareId(declareId)
                .userId(userId)
                .studentCommentId(studentCommentId)
                .theme(theme)
                .content(content)
                .build();
    }
}
