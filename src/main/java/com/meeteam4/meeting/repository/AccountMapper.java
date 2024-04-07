package com.meeteam4.meeting.repository;


import com.meeteam4.meeting.entity.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    public Student findStudentByStudentId(int userId);

}
