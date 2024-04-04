package com.meeteam4.meeting.repository;

import com.meeteam4.meeting.entity.Teacher;
import com.meeteam4.meeting.entity.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public int saveStudent(Student user);

    public int saveTeacher(Teacher teacher);

}
