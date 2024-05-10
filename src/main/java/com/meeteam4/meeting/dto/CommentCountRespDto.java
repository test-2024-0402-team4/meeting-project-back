package com.meeteam4.meeting.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentCountRespDto {
    private int commentCount;
}
