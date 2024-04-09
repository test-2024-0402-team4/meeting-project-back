package com.meeteam4.meeting.repository;


import com.meeteam4.meeting.dto.PosterReqDto;
import com.meeteam4.meeting.dto.SearchProfilesRespDto;
import com.meeteam4.meeting.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface AccountMapper {

    public Student findStudentByStudentId(int userId);
    public int saveDates(DateRegister dateRegister);
    public int saveSubjects(SubjectRegister subjectRegister);
    public int saveRegions(RegionRegister regionRegister);
    public int saveClassType(ClassTypeRegister classTypeRegister);

    public SearchProfilesRespDto searchTeacherProfilesByUserId(int userId);


}
