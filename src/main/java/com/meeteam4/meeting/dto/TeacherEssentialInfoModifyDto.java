package com.meeteam4.meeting.dto;


import com.meeteam4.meeting.entity.TeacherIntroduce;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherEssentialInfoModifyDto {

    private int userId;

    private String teacherIntroduceContent;

    public TeacherIntroduce toIntroduceEntity() {
        return TeacherIntroduce.builder()
                .userId(userId)
                .teacherIntroduceContent(teacherIntroduceContent)
                .build();
    }
}
