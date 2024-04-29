package com.meeteam4.meeting.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchPosterRespDto {

    private int posterId;
    private int userId;
    private String title;
    private String genderType;
    private String studentType;
    private String regionName;
    private String content;
    private String email;
    private List<String> subjectName;
    private List<String> dateType;
    private List<String> classType;

}
