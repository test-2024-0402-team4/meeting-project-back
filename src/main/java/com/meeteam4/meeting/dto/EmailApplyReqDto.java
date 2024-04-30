package com.meeteam4.meeting.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmailApplyReqDto {

    private String name;
    private String age;
    private String teacherEmail;
    private String email;
    private String gender;
    private String studentType;
    private String region;
    private List<String> subjects;
    private List<String> dates;
    private List<String> classType;
}
