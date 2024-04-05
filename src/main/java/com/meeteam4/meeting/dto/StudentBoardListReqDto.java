package com.meeteam4.meeting.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentBoardListReqDto {
   private int page;
   private int count;
   private String searchText;
}
