package com.meeteam4.meeting.entity;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StudentType {

    private int studentTypeId;
    private String studentType;
}
