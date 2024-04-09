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
public class SubjectRegister {

    private int subjectRegisterId;
    private int userId;
    private List<Integer> subjectIds;

    private Subject subject;
}
