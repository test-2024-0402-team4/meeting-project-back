package com.meeteam4.meeting.dto;


import com.meeteam4.meeting.entity.DateRegister;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchPosterReqDto {
    private String nickname;
    private int userId;
    private int regionId;
    private int genderId;
    private int subjectId;
    private int classTypeId;

    private DateRegister dateRegister;
}
