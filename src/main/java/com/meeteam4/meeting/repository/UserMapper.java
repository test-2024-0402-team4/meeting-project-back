package com.meeteam4.meeting.repository;

import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public int saveStudent(Student student);

    public int saveTeacher(Teacher teacher);

    public Student findStudentUsername(String username);
    public Teacher findTeacherUsername(String username);

    public Student findStudentNickname(String nickname);
    public Teacher findTeacherNickname(String nickname);

    public Student findStudentEmail(String email);
    public Teacher findTeacherEmail(String email);
}
