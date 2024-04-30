package com.meeteam4.meeting.dto;


import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentProfileModifyDto {

    private int userId;
    private String nickname;
    private int regionId;
    private String phoneNumber;

    public User toUserEntity() {
        return User.builder()
                .userId(userId)
                .nickname(nickname)
                .build();
    }

    public Student toStudentEntity() {
        return Student.builder()
                .userId(userId)
                .regionId(regionId)
                .phoneNumber(phoneNumber)
                .build();
    }
}
