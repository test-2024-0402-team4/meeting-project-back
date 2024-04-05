package com.meeteam4.meeting.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassType {
    private int classTypeId;
    private String classType;
}
