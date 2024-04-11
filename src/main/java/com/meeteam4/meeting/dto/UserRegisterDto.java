package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.Region;
import com.meeteam4.meeting.entity.RegionRegister;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
    private int userId;
    private String regionName;

    private Region region;
    private RegionRegister regionRegister;
}
