package com.meeteam4.meeting.dto;

import lombok.Data;

@Data
public class StudyBoardListReqDto {
    private int page;
    private int count;
    private String searchText;
}
