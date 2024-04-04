package com.meeteam4.meeting.dto;


import com.meeteam4.meeting.entity.Teacher;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignupTeacherReqDto {

    private String username;
    private String nickname;
    private String password;
    private String name;
    private String email;
    private String birthDate;
    private int genderId;
    private int roleId;
    private int universityId;
    private int graduateStateId;
    private int phoneNumber;
    private int regionId;

    public Teacher toEntity() {
        return Teacher.builder()
                .username(username)
                .nickname(nickname)
                .password(password)
                .name(name)
                .email(email)
                .birthDate(birthDate)
                .genderId(genderId)
                .roleId(roleId)
                .universityId(universityId)
                .graduateStateId(graduateStateId)
                .phoneNumber(phoneNumber)
                .regionId(regionId)
                .build();
    }
}
