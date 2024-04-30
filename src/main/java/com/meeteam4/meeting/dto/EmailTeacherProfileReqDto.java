package com.meeteam4.meeting.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class EmailTeacherProfileReqDto {

    private String email;
    private int userId;
}
