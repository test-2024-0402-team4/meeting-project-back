package com.meeteam4.meeting.entity;

import com.meeteam4.meeting.dto.DeclareUserRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeclareUser {
    private int declareId;
    private int userId;
    private String theme;
    private String content;
    private LocalDateTime createDate;

    private User user;

    public DeclareUserRespDto toDeclareUserRespDto() {

        return DeclareUserRespDto.builder()
                .userId(userId)
                .name(user.getName())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .roleId(user.getRoleId())
                .theme(theme)
                .content(content)
                .createDate(createDate)
                .build();
    }


}
