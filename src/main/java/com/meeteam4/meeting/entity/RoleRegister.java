package com.meeteam4.meeting.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RoleRegister {
    private int roleRegisterId;
    private int userId;
    private int roleId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
