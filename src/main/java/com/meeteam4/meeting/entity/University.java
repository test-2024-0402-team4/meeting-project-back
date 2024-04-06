package com.meeteam4.meeting.entity;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class University {

    private int universityId;
    private String universityName;
    private int universityRegionId;
}
