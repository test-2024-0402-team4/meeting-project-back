package com.meeteam4.meeting.entity;

import com.meeteam4.meeting.dto.NoticeBoardListRespDto;
import com.meeteam4.meeting.dto.NoticeBoardPageRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    private int noticeId;
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public NoticeBoardListRespDto toNoticeBoardListRespDto(){
        return NoticeBoardListRespDto.builder()
                .noticeId(noticeId)
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }

    public NoticeBoardPageRespDto toNoticeBoardPageRespDto(){
        return NoticeBoardPageRespDto.builder()
                .noticedId(noticeId)
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }

}
