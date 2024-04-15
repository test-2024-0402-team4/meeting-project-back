package com.meeteam4.meeting.dto;

import com.meeteam4.meeting.entity.Poster;
import com.meeteam4.meeting.entity.PosterClassTypeRegister;
import com.meeteam4.meeting.entity.PosterDateRegister;
import com.meeteam4.meeting.entity.PosterSubjectRegister;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PosterReqDto {
    private int posterId;
    private int userId;
    private String title;
    private int genderId;
    private int studentTypeId;
    private int regionId;
    private List<Integer> subjectIds;
    private List<Integer> dateIds;
    private List<Integer> classTypeIds;
    private String content;

    public Poster toEntity(){
        return Poster.builder()
                .posterId(posterId)
                .userId(userId)
                .title(title)
                .genderId(genderId)
                .studentTypeId(studentTypeId)
                .regionId(regionId)
                .content(content)
                .build();
    }
    public PosterSubjectRegister toPosterSubjectRegisterEntity(int posterId) {
        return PosterSubjectRegister.builder()
                .posterId(posterId)
                .subjectIds(subjectIds)
                .build();
    }
    public PosterDateRegister toPosterDateRegisterEntity(int posterId) {
        return PosterDateRegister.builder()
                .posterId(posterId)
                .dateIds(dateIds)
                .build();
    }
    public PosterClassTypeRegister toPosterClassTypeRegisterEntity(int posterId) {
        return PosterClassTypeRegister.builder()
                .posterId(posterId)
                .classTypeIds(classTypeIds)
                .build();
    }
}
