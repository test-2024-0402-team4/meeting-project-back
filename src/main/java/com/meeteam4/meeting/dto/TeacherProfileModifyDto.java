package com.meeteam4.meeting.dto;


import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.entity.Teacher;
import com.meeteam4.meeting.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherProfileModifyDto {

    private int userId;
    private String nickname;
    private int universityId;
    private String departmentName;
    private int graduateStateId;
    private String phoneNumber;

    public User toUserEntity() {
        return User.builder()
                .userId(userId)
                .nickname(nickname)
                .build();
    }

    public Teacher toTeacherEntity() {
        return Teacher.builder()
                .userId(userId)
                .universityId(universityId)
                .departmentName(departmentName)
                .graduateStateId(graduateStateId)
                .phoneNumber(phoneNumber)
                .build();
    }
}
