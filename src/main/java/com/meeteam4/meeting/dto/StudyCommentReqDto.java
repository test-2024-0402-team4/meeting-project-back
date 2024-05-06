package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.StudyComment;
import lombok.Data;

@Data
public class StudyCommentReqDto {
    private int studyCommentId;
    private int studyBoardId;
    private int userId;
    private String nickname;
    private String comment;

    public StudyComment toEntity(){
        return StudyComment.builder()
                .studyBoardId(studyBoardId)
                .userId(userId)
                .nickname(nickname)
                .comment(comment)
                .build();
    }
}
