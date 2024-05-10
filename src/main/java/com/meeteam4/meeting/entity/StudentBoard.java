package com.meeteam4.meeting.entity;

import com.meeteam4.meeting.dto.StudentBoardListRespDto;
import com.meeteam4.meeting.dto.StudentBoardPageRespDto;
import com.meeteam4.meeting.dto.StudyBoardListRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentBoard {
    private int studentBoardId;
    private int studentId;
    private int userId;
    private String nickname;
    private String title;
    private String content;
    private String theme;
    private int viewCount;
    private int genderId;
    private int commentCount;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public StudentBoardListRespDto toStudentBoardListRespDto(){
        return StudentBoardListRespDto.builder()
                .studentBoardId(studentBoardId)
                .userId(userId)
                .nickname(nickname)
                .title(title)
                .content(content)
                .theme(theme)
                .viewCount(viewCount)
                .genderId(genderId)
                .commentCount(commentCount)
                .createDate(createDate)
                .build();
    }

    public StudentBoardPageRespDto toStudentBoardPageRespDto(){
        return StudentBoardPageRespDto.builder()
                .studentBoardId(studentBoardId)
                .studentId(studentId)
                .userId(userId)
                .nickname(nickname)
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .createDate(createDate)
                .build();
    }
    public StudentBoardPageRespDto toStudentId(){
        return StudentBoardPageRespDto.builder()
                .studentId(studentId)
                .build();
    }





}
