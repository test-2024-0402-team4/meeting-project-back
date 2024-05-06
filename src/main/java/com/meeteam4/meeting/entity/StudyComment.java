package com.meeteam4.meeting.entity;

import com.meeteam4.meeting.dto.StudyCommentRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyComment {
    private int studyCommentId;
    private int studyBoardId;
    private int userId;
    private String nickname;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public StudyCommentRespDto toStudyCommentRespDto(){
        return StudyCommentRespDto.builder()
                .studyCommentId(studyCommentId)
                .studyBoardId(studyBoardId)
                .userId(userId)
                .nickname(nickname)
                .comment(comment)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }

}
