package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.StudyComment;
import lombok.Data;

@Data
public class UpdateStudyCommentReqDto {
    private int studyCommentId;
    private int studyBoardId;
    private int userId;
    private String comment;

    public StudyComment toEntity(){
        return StudyComment.builder()
                .studyCommentId(studyCommentId)
                .studyBoardId(studyBoardId)
                .userId(userId)
                .comment(comment)
                .build();
    }
}
