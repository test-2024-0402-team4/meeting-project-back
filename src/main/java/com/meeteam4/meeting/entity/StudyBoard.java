package com.meeteam4.meeting.entity;


import com.meeteam4.meeting.dto.StudentBoardListRespDto;
import com.meeteam4.meeting.dto.StudyBoardListRespDto;
import com.meeteam4.meeting.dto.StudyBoardPageRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyBoard {
    private int studyBoardId;
    private int userId;
    private String nickname;
    private String title;
    private String content;
    private int viewCount;
    private int genderId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private int commentCount;

    public StudyBoardListRespDto toStudyBoardListRespDto(){
        return StudyBoardListRespDto.builder()
                .studyBoardId(studyBoardId)
                .userId(userId)
                .nickname(nickname)
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .genderId(genderId)
                .commentCount(commentCount)
                .createDate(createDate)
                .build();
    }

    public StudyBoardPageRespDto toStudyBoardPageRespDto(){
        return StudyBoardPageRespDto.builder()
                .studyBoardId(studyBoardId)
                .userId(userId)
                .nickname(nickname)
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .createDate(createDate)
                .build();
    }

    public StudyBoardPageRespDto getUserIdByStudyBoardId(){
        return StudyBoardPageRespDto.builder()
                .userId(userId)
                .build();
    }
}
