package com.meeteam4.meeting.repository;


import com.meeteam4.meeting.dto.PosterReqDto;
import com.meeteam4.meeting.dto.SearchProfilesReqDto;
import com.meeteam4.meeting.dto.SearchProfilesRespDto;
import com.meeteam4.meeting.dto.UserRegisterDto;
import com.meeteam4.meeting.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.Date;
import java.util.List;

@Mapper
public interface AccountMapper {

    public Student findStudentByStudentId(int userId);
    public int saveDates(DateRegister dateRegister);
    public int saveSubjects(SubjectRegister subjectRegister);
    public int saveRegions(RegionRegister regionRegister);
    public int saveClassType(ClassTypeRegister classTypeRegister);

    public List<Integer> searchUserIds(@Param("nickname") String nickname,
                                       @Param("genderId") int genderId,
                                       @Param("regionIds") List<Integer> regionId,
                                       @Param("subjectIds") List<Integer> subjectId,
                                       @Param("classTypeIds") List<Integer> classTypeId,
                                       @Param("dateIds") List<Integer> dateId
                                       );
    public List<User> getTeacherProfiles(@Param("userIds") List<Integer> distinctUserIds);


    public int saveStudentPoster(Poster poster);
    public int savePosterDate(PosterDateRegister posterDateRegister);
    public int savePosterSubjectIds(PosterSubjectRegister posterSubjectRegister);
    public int savePosterClassTypeIds(PosterClassTypeRegister posterClassTypeRegister);

    public List<Integer> searchPosterIds(@Param("regionId") int regionId,
                                         @Param("subjectIds") List<Integer> subjectId,
                                         @Param("dateIds") List<Integer> dateId,
                                         @Param("classTypeIds") List<Integer> classTypeId);

    public List<Poster> getPosters(@Param("posterIds") List<Integer> posterIds);

    public int saveImgUrl(UserImgUrl userImgUrl);

    public User getStudentProfile(int userId);

}
