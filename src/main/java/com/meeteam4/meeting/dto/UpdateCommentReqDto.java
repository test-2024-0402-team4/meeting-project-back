package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.StudentComment;
import lombok.Data;

@Data
public class UpdateCommentReqDto {
    private int studentCommentId;
    private int studentBoardId;
    private int studentUserId;
    private String comment;

    public StudentComment toEntity(){
        return StudentComment.builder()
                .studentCommentId(studentCommentId)
                .studentBoardId(studentBoardId)
                .studentUserId(studentUserId)
                .comment(comment)
                .build();
    }

}
