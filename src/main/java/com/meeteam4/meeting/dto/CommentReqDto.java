package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.Comment;
import lombok.Data;

@Data
public class CommentReqDto {

    private int studentCommentId;
    private int studentBoardId;
    private int studentUserId;
    private String comment;

    public Comment toEntity(){
        return Comment.builder()
                .studentCommentId(studentCommentId)
                .studentBoardId(studentBoardId)
                .studentUserId(studentUserId)
                .comment(comment)
                .build();
    }
}
