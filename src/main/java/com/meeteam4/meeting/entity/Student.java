package com.meeteam4.meeting.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private int studentId;
    private int userId;
    private String birthDate;
    private int genderId;
    private int studentTypeId;
    private String phoneNumber;
    private int regionId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private User user;
    private Gender gender;
    private StudentType studentType;
    private Region region;
}
