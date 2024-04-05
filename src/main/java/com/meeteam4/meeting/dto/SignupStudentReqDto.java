package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.Student;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Builder
@Data
public class SignupStudentReqDto {

        private String birthDate;
        private int genderId;
        private int studentTypeId;
        private int subjectId;
        private String phoneNumber;
        private int regionId;

    public Student toEntity() {
        return Student.builder()
                .birthDate(birthDate)
                .genderId(genderId)
                .studentTypeId(studentTypeId)
                .subjectId(subjectId)
                .phoneNumber(phoneNumber)
                .regionId(regionId)
                .build();
    }
}
