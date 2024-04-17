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
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public StudyBoardListRespDto toStudyBoardListRespDto(){
        return StudyBoardListRespDto.builder()
                .studyBoardId(studyBoardId)
                .userId(userId)
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .createDate(createDate)
                .build();
    }

    public StudyBoardPageRespDto toStudyBoardPageRespDto(){
        return StudyBoardPageRespDto.builder()
                .studyBoardId(studyBoardId)
                .userId(userId)
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .createDate(createDate)
                .build();
    }
}
