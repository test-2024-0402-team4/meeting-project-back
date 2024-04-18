package com.meeteam4.meeting.repository;

import com.meeteam4.meeting.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    public int saveBoard(StudentBoard board);

    public List<StudentBoard> searchBoard(
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("searchText") String searchText
    );

    public int getStudentCount(
            @Param("count") int count,
            @Param("searchText") String searchText
            );

    public StudentBoard getSingleBoard(int studentBoardId);

    public int deleteBoardByBoardId(int studentBoardId);

    public int updateBoardByBoardId(StudentBoard studentBoard);

    public int saveComment(StudentComment comment);

    public List<StudentComment> getStudentComment(int studentBoardId);

    public int deleteStudentCommentByCommentId(int studentCommentId);

    public int updateCommentByCommentId(StudentComment studentComment);

    public int saveTeacherBoard(TeacherBoard teacherBoard);

    public List<TeacherBoard> searchTeacherBoard(
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("searchText") String searchText
    );

    public int getTeacherCount(
            @Param("count") int count,
            @Param("searchText") String searchText
    );
    public TeacherBoard getSingleTeacherBoard(int teacherBoardId);

    public int deleteTeacherBoardByBoardId(int teacherBoardId);

    public int updateTeacherBoardByBoardId(TeacherBoard teacherBoard);

    public int saveTeacherComment(TeacherComment teacherComment);

    public List<TeacherComment> getTeacherComment(int teacherBoardId);

    public int deleteTeacherCommentByCommentId(int teacherCommentId);

    public int updateTeacherCommentByCommentId(TeacherComment teacherComment);

    public int saveStudyBoard(StudyBoard studyBoard);

    public List<StudyBoard> searchStudyBoard(
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("searchText") String searchText
    );
    public int getStudyCount(
            @Param("count") int count,
            @Param("searchText") String searchText
    );
    public StudyBoard getSingleStudyBoard(int studyBoardId);

    public int deleteStudyBoardByBoardId(int studyBoardId);

    public int updateStudyBoardByBoardId(StudyBoard studyBoard);


    public int saveStudyComment(StudyComment studyComment);

    public List<StudyComment> getStudyComment(int studyBoardId);

    public int deleteStudyCommentByCommentId(int studyCommentId);

    public int updateStudyCommentByCommentId(StudyComment studyComment);


    public List<Notice> searchNoticeBoard(
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("searchText") String searchText
    );
    public int getNoticeCount(
            @Param("count") int count,
            @Param("searchText") String searchText
    );
    public Notice getSingleNoticeBoard(int noticeId);











}
