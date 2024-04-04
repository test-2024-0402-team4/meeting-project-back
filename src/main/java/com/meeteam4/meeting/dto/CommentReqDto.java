package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.Comment;
import lombok.Data;

@Data
public class CommentReqDto {

    private int commentId;
    private int boardId;
    private int userId;
    private String comment;

    public Comment toEntity(){
        return Comment.builder()
                .commentId(commentId)
                .boardId(boardId)
                .userId(userId)
                .comment(comment)
                .build();
    }
}
