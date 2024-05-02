package com.meeteam4.meeting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherProfileReqDto {

    private int userId;
    private List<Integer> subjectIds;
    private List<Integer> regionIds;
    private List<Integer> dateIds;
    private List<Integer> classTypeIds;
    private String teacherIntroduceContent;


}
