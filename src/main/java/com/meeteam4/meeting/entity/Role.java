package com.meeteam4.meeting.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role {
    private int roleId;
    private String roleName;
}
