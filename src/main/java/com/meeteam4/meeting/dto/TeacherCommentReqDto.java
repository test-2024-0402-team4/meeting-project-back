package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.TeacherComment;
import lombok.Data;

@Data
public class TeacherCommentReqDto {
    private int teacherCommentId;
    private int teacherBoardId;
    private int teacherId;
    private String nickname;
    private String comment;

    public TeacherComment toEntity(){
        return TeacherComment.builder()
                .teacherBoardId(teacherBoardId)
                .teacherId(teacherId)
                .nickname(nickname)
                .comment(comment)
                .build();
    }
}
