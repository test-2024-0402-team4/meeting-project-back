package com.meeteam4.meeting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDataRespDto {
    private String nickname;
    private String email;
    private String genderType;
    private String studentType;
    private String regionName;
}
