package com.meeteam4.meeting.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    private int studentId;
    private String username;
    private String nickname;
    private String password;
    private String name;
    private String email;
    private String birthDate;
    private int genderId;
    private String genderType;
    private int roleId;
    private String roleName;
    private int studentTypeId;
    private String studentType;
    private int subjectId;
    private String phoneNumber;
    private int regionId;
    private String regionName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;



}
