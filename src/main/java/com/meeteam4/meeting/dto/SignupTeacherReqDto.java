package com.meeteam4.meeting.dto;


import com.meeteam4.meeting.entity.Teacher;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Builder
@Data
public class SignupTeacherReqDto {

    private String birthDate;
    private int genderId;
    private int universityId;
    private int graduateStateId;
    private String phoneNumber;
    private int regionId;

    public Teacher toEntity() {
        return Teacher.builder()
                .birthDate(birthDate)
                .genderId(genderId)
                .universityId(universityId)
                .graduateStateId(graduateStateId)
                .phoneNumber(phoneNumber)
                .regionId(regionId)
                .build();

    }
}
