package com.meeteam4.meeting.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Region {
    private int regionId;
    private String regionName;
}
