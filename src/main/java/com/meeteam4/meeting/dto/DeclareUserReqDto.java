package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.DeclareUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeclareUserReqDto {
    private int userId;
    private String theme;
    private String content;

    public DeclareUser toDeclareUser() {
        return DeclareUser.builder()
                .userId(userId)
                .theme(theme)
                .content(content)
                .build();
    }

}
