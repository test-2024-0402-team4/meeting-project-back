package com.meeteam4.meeting.repository;

import com.meeteam4.meeting.entity.*;
import org.apache.ibatis.annotations.Mapper;

import javax.xml.crypto.Data;
import java.util.List;

@Mapper
public interface AuthOptionMapper {

    public List<Region> getRegion();
    public List<StudentType> getStudentType();
    public List<University> getUniversity();
    public List<GraduateState> getGraduateState();

    public List<Subject> getSubject();
    public List<ClassType> getClassType();
    public List<Date> getDate();
}
