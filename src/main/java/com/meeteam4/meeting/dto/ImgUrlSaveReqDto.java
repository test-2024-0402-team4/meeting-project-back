package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.UserImgUrl;
import lombok.Data;

@Data
public class ImgUrlSaveReqDto {
    private int userImgUrlId;
    private int userId;
    private String userImgUrl;

    public UserImgUrl toEntity() {
        return UserImgUrl.builder()
                .userId(userId)
                .userImgUrl(userImgUrl)
                .build();
    }
}
