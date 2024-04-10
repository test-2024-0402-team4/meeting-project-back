package com.meeteam4.meeting.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchProfilesRespDto {
    private int userId;
    private String nickname;
    private String email;
    private int genderId;
    private int universityId;
    private String departmentName;
    private int graduateStateId;
    private List<Integer> subjectIds;
    private List<Integer> classTypeIds;
    private List<Integer> dateIds;
    private List<String> regionNames;

}
