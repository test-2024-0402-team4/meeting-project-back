package com.meeteam4.meeting.dto;


import com.meeteam4.meeting.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@Builder
public class SignupUserDto {

    private String name;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private int roleId;

    public User toEntity(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .name(name)
                .username(username)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .email(email)
                .roleId(roleId)
                .build();
    }
}
