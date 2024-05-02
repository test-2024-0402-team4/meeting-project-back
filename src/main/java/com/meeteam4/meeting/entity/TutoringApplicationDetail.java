package com.meeteam4.meeting.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TutoringApplicationDetail {

    private int TutoringApplicationDetailId;
    private int studentUserId;
    private int teacherUserId;
    private LocalDateTime applyDate;

}
