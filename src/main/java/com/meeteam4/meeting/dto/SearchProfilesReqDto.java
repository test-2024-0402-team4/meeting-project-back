package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchProfilesReqDto {
    private String nickname;
    private Integer genderId;
    private List<Integer> subjectIds;
    private List<Integer> regionIds;
    private List<Integer> dateIds;
    private List<Integer> classTypeIds;



}
