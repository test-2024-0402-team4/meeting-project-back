package com.meeteam4.meeting.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OAuth2MergeReqDto {

    private String username;
    private String oAuth2Name;
    private String password;
    private String providerName;
}
