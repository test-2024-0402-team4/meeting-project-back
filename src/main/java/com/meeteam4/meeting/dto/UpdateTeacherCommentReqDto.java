package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.TeacherComment;
import lombok.Data;

@Data
public class UpdateTeacherCommentReqDto {
    private int teacherCommentId;
    private int teacherBoardId;
    private int teacherId;
    private String comment;

    public TeacherComment toEntity(){
        return TeacherComment.builder()
                .teacherCommentId(teacherCommentId)
                .teacherBoardId(teacherBoardId)
                .teacherId(teacherId)
                .comment(comment)
                .build();
    }
}
