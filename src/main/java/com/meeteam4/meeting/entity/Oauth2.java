package com.meeteam4.meeting.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Oauth2 {

    private int oAuth2Id;
    private String oAuth2Name;
    private int userId;
    private String providerName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
