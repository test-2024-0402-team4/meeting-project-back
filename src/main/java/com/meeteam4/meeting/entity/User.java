package com.meeteam4.meeting.entity;

import com.meeteam4.meeting.dto.SearchProfilesRespDto;
import com.meeteam4.meeting.dto.StudentProfileRespDto;
import com.meeteam4.meeting.security.PrincipalUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.JSONUtil;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private String userImgUrl;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private Gender gender;
    private Teacher teacher;
    private Student student;
    private StudentType studentType;
    private University university;
    private GraduateState graduateState;
    private List<SubjectRegister> subjectRegister;
    private List<DateRegister> dateRegister;
    private List<ClassTypeRegister> classTypeRegister;
    private List<RegionRegister> regionRegister;
    private List<RoleRegister> roleRegisters;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return roleRegisters.stream()
                .map(roleRegister ->
                        new SimpleGrantedAuthority(roleRegister.getRole().getRoleName()))
                .collect(Collectors.toList());
    }

    public PrincipalUser toprincipalUser() {
        return PrincipalUser.builder()
                .userId(userId)
                .username(username)
                .name(name)
                .roleId(roleId)
                .authorities(getAuthorities())
                .build();
    }
    public SearchProfilesRespDto toSearchProfilesRespDto() {
        SearchProfilesRespDto searchProfile = new SearchProfilesRespDto();
        searchProfile.setUserId(userId);
        searchProfile.setNickname(nickname);
        searchProfile.setEmail(email);
        searchProfile.setGraduateState(graduateState.getGraduateState());
        searchProfile.setGenderType(gender.getGenderType());


        return searchProfile;
    }

    public StudentProfileRespDto toStudentProfileRespDto(){
        return StudentProfileRespDto.builder()
                .userId(userId)
                .name(name)
                .email(email)
                .nickname(nickname)
                .studentType(studentType.getStudentType())
                .userImgUrl(userImgUrl)
                .genderType(gender.getGenderType())
                .birthDate(teacher.getBirthDate())
                .regionName(regionRegister.get(0).getRegion().getRegionName())
                .roleNameKor(roleRegisters.get(0).getRole().getRoleNameKor())
                .build();
    }
}