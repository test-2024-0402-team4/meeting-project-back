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
public class Teacher {

    private int teacherId;
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
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
