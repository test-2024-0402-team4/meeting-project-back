package com.meeteam4.meeting.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherIntroduce {

    private int teacherIntroduceId;
    private int userId;
    private String teacherIntroduceContent;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
