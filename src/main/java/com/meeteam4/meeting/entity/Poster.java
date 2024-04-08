package com.meeteam4.meeting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Poster {
    private int posterId;
    private int userId;
    private int regionId;
    private int genderId;
    private int subjectId;
    private int classTypeId;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
