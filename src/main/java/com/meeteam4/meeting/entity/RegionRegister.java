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
public class RegionRegister {
    private int regionRegisterId;
    private int userId;
    private List<Integer> regionIds;
    private int regionId;
}
