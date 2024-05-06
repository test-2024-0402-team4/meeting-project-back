package com.meeteam4.meeting.entity;


import com.meeteam4.meeting.dto.GenderRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    private int teacherId;
    private int userId;
    private String birthDate;
    private int genderId;
    private String genderType;
    private int universityId;
    private String departmentName;
    private int graduateStateId;
    private String phoneNumber;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;


    public GenderRespDto toEntity(){
        return GenderRespDto.builder()
                .genderType(genderType)
                .build();
    }

}
