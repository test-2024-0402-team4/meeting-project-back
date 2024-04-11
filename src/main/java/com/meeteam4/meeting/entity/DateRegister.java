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
public class DateRegister {
    private int dateRegisterId;
    private int userId;
    private int dateId;
    private List<Integer> dateIds;

    private Date date;
}
