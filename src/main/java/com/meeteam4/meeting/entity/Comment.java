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

public class Comment {
    private int commentId;
    private int boardId;
    private int userId;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
