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

    public int deleteAccount(int userId);
    public int deleteTeacher(int userId);
    public int deleteTeacherIntroduce(int userId);
    public int deleteSubjectRegister(int userId);
    public int deleteStudyComment(int userId);
    public int deleteStudyBoard(int userId);
    public int deleteStudent(int userId);
    public int deleteRoleRegister(int userId);
    public int deleteRegionRegister(int userId);
    public int deletePoster(int userId);
    public int deleteOauth2(int userId);
    public int deleteDateRegister(int userId);
    public int deleteClassTypeRegister(int userId);
    public int deleteTeacherComment(int userId);
    public int deleteTeacherBoard(int userId);
    public int deleteStudentBoard(int userId);
    public int deleteStudentComment(int userId);

}
