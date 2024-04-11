package com.meeteam4.meeting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchProfilesRespDto {
    private int userId;
    private String nickname;
    private String email;
    private int genderId;
    private String  universityName;
    private String departmentName;
    private int graduateStateId;
    private List<String> subjectNames;
    private List<String> classTypeNames;
    private List<String> dateNames;
    private List<String> regionNames;


}
