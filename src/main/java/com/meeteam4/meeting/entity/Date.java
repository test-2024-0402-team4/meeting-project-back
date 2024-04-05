package com.meeteam4.meeting.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Date {
    private int dateId;
    private String dateType;
}
