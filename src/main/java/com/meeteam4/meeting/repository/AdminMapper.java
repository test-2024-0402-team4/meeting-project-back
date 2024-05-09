package com.meeteam4.meeting.repository;

import com.meeteam4.meeting.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {

    public void disableAccount(@Param("userId") int userId);
    public List<StudentBoardDeclare> getDeclareStudentBoard();
    public List<TeacherBoardDeclare> getDeclareTeacherBoard();
    public List<StudyBoardDeclare> getDeclareStudyBoard();
    public List<StudentCommentDeclare> getDeclareStudentComment();
    public List<TeacherCommentDeclare> getDeclareTeacherComment();
    public List<StudyCommentDeclare> getDeclareStudyComment();

    public Integer getUserStatus(@Param("userId") int userId);

    public List<DeclareUser> getDeclareUser();

}
