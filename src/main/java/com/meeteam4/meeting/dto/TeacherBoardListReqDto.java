package com.meeteam4.meeting.dto;

import lombok.Data;

@Data
public class TeacherBoardListReqDto {
    private int page;
    private int count;
    private String searchText;
}
