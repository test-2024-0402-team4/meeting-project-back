package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.DateRegister;
import com.meeteam4.meeting.entity.Poster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PosterReqDto {
    private int userId;
    private int regionId;
    private int genderId;
    private int subjectId;
    private int classTypeId;
    private List<Integer> dateIds;
    private String content;


    public Poster toPosterEntity() {
        return Poster.builder()
                .userId(userId)
                .regionId(regionId)
                .genderId(genderId)
                .subjectId(subjectId)
                .classTypeId(classTypeId)
                .content(content)
                .build();
    }


}
