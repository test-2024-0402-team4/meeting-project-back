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
public class PosterClassTypeRegister {
    private int posterClassTypeRegisterId;
    private int posterId;
    private int classTypeId;
    private List<Integer> classTypeIds;

    private ClassType classType;



}
