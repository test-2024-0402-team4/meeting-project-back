package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.StudentComment;
import lombok.Data;

@Data
public class CommentReqDto {

    private int studentCommentId;
    private int studentBoardId;
    private int studentUserId;
    private String nickname;
    private String comment;

    public StudentComment toEntity(){
        return StudentComment.builder()
                .studentBoardId(studentBoardId)
                .studentUserId(studentUserId)
                .nickname(nickname)
                .comment(comment)
                .build();
    }
}
