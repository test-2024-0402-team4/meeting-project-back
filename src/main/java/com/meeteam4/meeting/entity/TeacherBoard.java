package com.meeteam4.meeting.entity;


import com.meeteam4.meeting.dto.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherBoard {
    private int teacherBoardId;
    private int teacherId;
    private String nickname;
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private int userId;

    public TeacherBoardListRespDto toTeacherBoardListRespDto(){
        return TeacherBoardListRespDto.builder()
                .teacherBoardId(teacherBoardId)
                .teacherId(teacherId)
                .nickname(nickname)
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .createDate(createDate)
                .build();
    }

    public TeacherBoardPageRespDto toTeacherBoardPageRespDto(){
        return TeacherBoardPageRespDto.builder()
                .teacherBoardId(teacherBoardId)
                .teacherId(teacherId)
                .nickname(nickname)
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .createDate(createDate)
                .build();
    }

    public TeacherIdRespDto toTeacherIdRespDto(){
        return TeacherIdRespDto.builder()
                .teacherId(teacherId)
                .build();
    }
}
