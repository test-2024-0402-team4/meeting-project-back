package com.meeteam4.meeting.dto;

import lombok.Data;

@Data
public class NoticeBoardListReqDto {
    private int page;
    private int count;
    private String searchText;
}
