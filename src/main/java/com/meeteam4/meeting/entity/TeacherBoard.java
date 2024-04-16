package com.meeteam4.meeting.entity;


import com.meeteam4.meeting.dto.StudentBoardListRespDto;
import com.meeteam4.meeting.dto.TeacherBoardListReqDto;
import com.meeteam4.meeting.dto.TeacherBoardListRespDto;
import com.meeteam4.meeting.dto.TeacherBoardPageRespDto;
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
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public TeacherBoardListRespDto toTeacherBoardListRespDto(){
        return TeacherBoardListRespDto.builder()
                .teacherBoardId(teacherBoardId)
                .teacherId(teacherId)
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
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .createDate(createDate)
                .build();
    }
}
