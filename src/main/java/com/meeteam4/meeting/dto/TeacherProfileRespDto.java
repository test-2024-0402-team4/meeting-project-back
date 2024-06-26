package com.meeteam4.meeting.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherProfileRespDto {
    private int userId;
    private String nickname;
    private String userImgUrl;
    private String genderType;
    private String regionName;
    private String roleNameKor;
    private String birthDate;
}
