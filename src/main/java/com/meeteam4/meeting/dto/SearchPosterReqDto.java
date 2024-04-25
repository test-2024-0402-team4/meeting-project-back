package com.meeteam4.meeting.dto;


import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class SearchPosterReqDto {

    private List<Integer> regionIds;
    private List<Integer> subjectIds;
    private List<Integer> dateIds;
    private List<Integer> studentTypeIds;

}
