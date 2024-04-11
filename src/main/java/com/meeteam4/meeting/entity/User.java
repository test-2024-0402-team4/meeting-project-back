package com.meeteam4.meeting.entity;

import com.meeteam4.meeting.dto.SearchProfilesRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int userId;
    private String name;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private int roleId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private Gender gender;
    private Teacher teacher;
    private University university;
    private GraduateState graduateState;
    private List<SubjectRegister> subjectRegister;
    private List<DateRegister> dateRegister;
    private List<ClassTypeRegister> classTypeRegister;
    private List<RegionRegister> regionRegister;


    public SearchProfilesRespDto toSearchProfilesRespDto() {
        SearchProfilesRespDto searchProfile = new SearchProfilesRespDto();
        searchProfile.setUserId(userId);
        searchProfile.setNickname(nickname);
        searchProfile.setEmail(email);
        searchProfile.setGraduateState(graduateState.getGraduateState());
        searchProfile.setGenderType(gender.getGenderType());


        return searchProfile;
    }
}