package com.meeteam4.meeting.repository;

import com.meeteam4.meeting.entity.OAuth2;
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
    public int saveOAuth2(OAuth2 oAuth2);

    public User findByUsername(String username);
    public User findByNickname(String nickname);
    public User findByEmail(String email);

    public User findByName(@Param("name") String name, @Param("email") String email);
    public User findPassword(@Param("username") String username, @Param("email") String email);

    public User findUserByOAuth2Name(String oAuth2Name);

    public int modifyPassword(User user);
}
