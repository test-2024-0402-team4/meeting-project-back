package com.meeteam4.meeting.entity;

import com.meeteam4.meeting.dto.StudentCommentRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentComment {
    private int studentCommentId;
    private int studentBoardId;
    private int studentUserId;
    private String nickname;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String userImgUrl;

    public StudentCommentRespDto toStudentCommentRespDto(){
        return StudentCommentRespDto.builder()
                .studentCommentId(studentCommentId)
                .studentBoardId(studentBoardId)
                .studentUserId(studentUserId)
                .nickname(nickname)
                .comment(comment)
                .createDate(createDate)
                .updateDate(updateDate)
                .userImgUrl(userImgUrl)
                .build();
    }
}
