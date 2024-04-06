package com.meeteam4.meeting.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class User {

    private int userId;
    private String name;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private int roleId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
