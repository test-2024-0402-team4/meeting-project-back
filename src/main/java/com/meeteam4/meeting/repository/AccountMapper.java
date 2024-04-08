package com.meeteam4.meeting.repository;


import com.meeteam4.meeting.dto.PosterReqDto;
import com.meeteam4.meeting.entity.Poster;
import com.meeteam4.meeting.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface AccountMapper {

    public Student findStudentByStudentId(int userId);
    public int savePoster(Poster poster);
    public int saveDates(@Param("posterId") int posterId, List<Integer> dateIds);
}
