package com.meeteam4.meeting.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OAuth2 {

    private int oAuth2Id;
    private String oAuth2Name;
    private int userId;
    private String providerName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
