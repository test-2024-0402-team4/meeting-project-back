package com.meeteam4.meeting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeclareTeacherBoardCommentRespDto {
    private int userId;
    private String theme;
    private String content;
    private int teacherBoardId;
    private String nickname;
    private String comment;
    private String title;
    private LocalDateTime createDate; // 신고 된 시간
    private LocalDateTime updateDate; // 댓글 작성 된 시간
}
