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
public class ClassTypeRegister {
    private int classTypeRegisterId;
    private int userId;
    private List<Integer> classTypeIds;

    private ClassType classType;


}
