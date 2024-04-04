package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.Student;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignupStudentReqDto {

    private String username;
    private String nickname;
    private String password;
    private String name;
    private String email;
    private String birthDate;
    private int genderId;
    private int roleId;
    private int studentTypeId;
    private int subjectId;
    private String phoneNumber;
    private int regionId;

    public Student toEntity() {
        return Student.builder()
                .username(username)
                .nickname(nickname)
                .password(password)
                .name(name)
                .email(email)
                .birthDate(birthDate)
                .genderId(genderId)
                .roleId(roleId)
                .studentTypeId(studentTypeId)
                .subjectId(subjectId)
                .phoneNumber(phoneNumber)
                .regionId(regionId)
                .build();
    }
}
