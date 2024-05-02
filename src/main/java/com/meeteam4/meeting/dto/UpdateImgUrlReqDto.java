package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.User;
import lombok.Data;

@Data

public class UpdateImgUrlReqDto {
    private int userId;
    private String userImgUrl;

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .userImgUrl(userImgUrl)
                .build();
    }

}
