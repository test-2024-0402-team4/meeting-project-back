package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.Declaration;
import lombok.Data;

@Data
public class TeacherDeclareReqDto {
    private int declareId;
    private int userId;
    private int teacherBoardId;
    private int teacherCommentId;
    private String theme;
    private String content;

    public Declaration toEntity(){
        return Declaration.builder()
                .declareId(declareId)
                .userId(userId)
                .teacherBoardId(teacherBoardId)
                .theme(theme)
                .content(content)
                .build();
    }
    public Declaration teacherCommentDeclare(){
        return Declaration.builder()
                .declareId(declareId)
                .userId(userId)
                .teacherCommentId(teacherCommentId)
                .theme(theme)
                .content(content)
                .build();
    }

}
