package com.meeteam4.meeting.entity;

import com.meeteam4.meeting.dto.TeacherCommentRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherComment {
    private int teacherCommentId;
    private int teacherBoardId;
    private int teacherId;
    private String nickname;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String userImgUrl;

    public TeacherCommentRespDto toTeacherCommentRespDto(){
        return TeacherCommentRespDto.builder()
                .teacherCommentId(teacherCommentId)
                .teacherBoardId(teacherBoardId)
                .teacherId(teacherId)
                .nickname(nickname)
                .comment(comment)
                .createDate(createDate)
                .updateDate(updateDate)
                .userImgUrl(userImgUrl)
                .build();
    }
}
