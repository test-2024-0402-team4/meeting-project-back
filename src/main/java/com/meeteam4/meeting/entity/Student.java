package com.meeteam4.meeting.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
public class Student {

    private int studentId;
    private int userId;
    private String birthDate;
    private int genderId;
    private int studentTypeId;
    private int subjectId;
    private String phoneNumber;
    private int regionId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
