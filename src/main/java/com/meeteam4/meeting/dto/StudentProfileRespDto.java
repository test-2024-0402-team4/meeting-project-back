package com.meeteam4.meeting.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentProfileRespDto {
    private int userId;
    private String nickname;
    private String userImgUrl;
    private String genderType;
    private String regionName;
    private String roleNameKor;
    private int studentId;
    private int teacherId;

}

