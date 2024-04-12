package com.meeteam4.meeting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Poster {
    private int posterId;
    private int userId;
    private String title;
    private int genderId;
    private int studentTypeId;
    private int regionId;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
