package com.meeteam4.meeting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Board {
    private int studentBoardId;
    private int studentId;
    private String title;
    private String content;
    private String theme;
    private int viewCount;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}