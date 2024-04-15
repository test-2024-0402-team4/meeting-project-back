package com.meeteam4.meeting.repository;

import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.entity.Teacher;
import com.meeteam4.meeting.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    public int saveUser(User user);
    public int saveStudent(Student student);
    public int saveTeacher(Teacher teacher);
    public int saveRole(@Param("userId") int userId, @Param("roleId") int roleId);

    public User findByUsername(String username);
    public User findByNickname(String nickname);
    public User findByEmail(String email);
}
