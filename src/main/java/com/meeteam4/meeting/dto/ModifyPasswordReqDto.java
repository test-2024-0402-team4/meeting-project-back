package com.meeteam4.meeting.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModifyPasswordReqDto {

    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,128}$", message = "영문, 숫자, 특수문자를 포함한 5~128자리 형식이어야 합니다.")
    private String newPassword;
    private String newPasswordCheck;
}
