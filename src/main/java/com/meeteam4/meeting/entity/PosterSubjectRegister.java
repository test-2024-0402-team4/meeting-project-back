package com.meeteam4.meeting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PosterSubjectRegister {
    private int posterSubjectRegisterId;
    private int posterId;
    private int subjectId;
    private List<Integer> subjectIds;

    private Subject subject;
}
