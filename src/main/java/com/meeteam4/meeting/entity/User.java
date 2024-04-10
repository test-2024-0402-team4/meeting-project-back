package com.meeteam4.meeting.entity;

import com.meeteam4.meeting.dto.SearchProfilesRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int userId;
    private String name;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private int roleId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private Student student;
    private Teacher teacher;

    public SearchProfilesRespDto toSearchProfilesRespDto() {
        return SearchProfilesRespDto
                .builder()
                .userId(userId)
                .nickname(nickname)
                .genderId(teacher.getGenderId())
                .email(email)
                .universityId(teacher.getUniversityId())
                .departmentName(teacher.getDepartmentName())
                .graduateStateId(teacher.getGraduateStateId())
                .build();
    }

}
