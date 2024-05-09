package com.meeteam4.meeting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeclareUserRespDto {
    private int userId;
    private String name;
    private String username;
    private String nickname;
    private String email;
    private int roleId;
    private String theme;
    private String content;
    private LocalDateTime createDate;

}
