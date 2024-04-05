package com.meeteam4.meeting.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Gender {
    private int genderId;
    private String genderType;
}
