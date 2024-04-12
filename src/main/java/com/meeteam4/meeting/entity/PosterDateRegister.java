package com.meeteam4.meeting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PosterDateRegister {
    private int posterDateRegisterId;
    private int posterId;
    private int dateId;
    private List<Integer> dateIds;

    private Date date;
    private Poster poster;
}
