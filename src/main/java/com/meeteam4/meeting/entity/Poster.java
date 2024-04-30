package com.meeteam4.meeting.entity;

import com.meeteam4.meeting.dto.SearchPosterReqDto;
import com.meeteam4.meeting.dto.SearchPosterRespDto;
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
public class Poster {
    private int posterId;
    private int userId;
    private String title;
    private int genderId;
    private int studentTypeId;
    private int regionId;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private User user;
    private List<PosterClassTypeRegister> posterClassTypeRegister;
    private List<PosterDateRegister> posterDateRegister;
    private List<PosterSubjectRegister> posterSubjectRegister;
    private Gender gender;
    private StudentType studentType;
    private Region region;

    public SearchPosterRespDto toSearchPosterRespDto() {
        return SearchPosterRespDto.builder()
                .posterId(posterId)
                .userId(userId)
                .title(title)
                .content(content)
                .genderType(gender.getGenderType())
                .studentType(studentType.getStudentType())
                .regionName(region.getRegionName())
                .email(user.getEmail())
                .build();
    }
}
