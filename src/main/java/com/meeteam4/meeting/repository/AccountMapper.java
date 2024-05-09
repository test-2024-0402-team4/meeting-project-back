package com.meeteam4.meeting.repository;


import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.Date;
import java.util.List;

@Mapper
public interface AccountMapper {

    public Student findStudentByStudentId(int userId);
    public int saveDates(DateRegister dateRegister);
    public int saveSubjects(SubjectRegister subjectRegister);
    public int saveRegions(RegionRegister regionRegister);
    public int saveClassType(ClassTypeRegister classTypeRegister);
    public int saveTeacherIntroduce(TeacherIntroduce teacherIntroduce);

    public int deleteDates(@Param("userId") int userId);
    public int deleteSubjects(@Param("userId") int userId);
    public int deleteRegions(@Param("userId") int userId);
    public int deleteClassTypes(@Param("userId") int userId);
    public int deleteTeacherIntroduce(@Param("userId") int userId);

    public int saveApplicationDetails(@Param("studentUserId") int studentUserId,@Param("teacherUserId") int teacherUserId);
    public List<Integer> getUserIdByApplicationDetails(@Param("studentUserId") int studentUserId);


    public List<Integer> searchUserIds(@Param("nickname") String nickname,
                                       @Param("genderId") int genderId,
                                       @Param("regionIds") List<Integer> regionId,
                                       @Param("subjectIds") List<Integer> subjectId,
                                       @Param("classTypeIds") List<Integer> classTypeId,
                                       @Param("dateIds") List<Integer> dateId
                                       );
    public List<User> getTeacherProfiles(@Param("userIds") List<Integer> distinctUserIds);

    public int modifyStudentProfile(Student student);
    public int modifyTeacherProfile(Teacher teacher);
    public int modifyTeacherIntroduce(TeacherIntroduce teacherIntroduce);
    public int modifyUserProfile(User user);

    public int saveStudentPoster(Poster poster);
    public int savePosterDate(PosterDateRegister posterDateRegister);
    public int savePosterSubjectIds(PosterSubjectRegister posterSubjectRegister);
    public int savePosterClassTypeIds(PosterClassTypeRegister posterClassTypeRegister);

    public int deleteStudentPoster(@Param("posterId") int posterId);
    public int deletePosterDate(@Param("posterId") int posterId);
    public int deletePosterSubjectIds(@Param("posterId") int posterId);
    public int deletePosterClassTypeIds(@Param("posterId") int posterId);
  
    public int modifyStudentPoster(Poster poster);

    public List<Integer> searchPosterIds(@Param("regionIds") List<Integer> regionId,
                                         @Param("subjectIds") List<Integer> subjectId,
                                         @Param("dateIds") List<Integer> dateId,
                                         @Param("studentTypeIds") List<Integer> studentTypeId);

    public List<Poster> getPosters(@Param("posterIds") List<Integer> posterIds);

    public Poster getPoster(@Param("posterId") int posterId);

    public List<Poster> getStudentMyPosters(@Param("userId") int userId);

    public Poster getStudentMyPoster(@Param("posterId") int posterId);

    public User getStudentProfile(int userId);

    public List<StudentBoard> searchStudentMypageBoards(
            @Param("userId") int userId,
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("searchText") String searchText
    );

    public int getStudentMypageCount(
            @Param("userId") int userId,
            @Param("count") int count,
            @Param("searchText") String searchText
    );

    public List<StudyBoard> searchStudyMypageBoards(
            @Param("userId") int userId,
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("searchText") String searchText
    );

    public int getStudyMypageCount(
            @Param("userId") int userId,
            @Param("count") int count,
            @Param("searchText") String searchText
    );

    public List<TeacherBoard> searchTeacherMypageBoards(
            @Param("userId") int userId,
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("searchText") String searchText
    );

    public int getTeacherMypageCount(
            @Param("userId") int userId,
            @Param("count") int count,
            @Param("searchText") String searchText
    );

    public List<StudyBoard> searchTeacherStudyMypageBoards(
            @Param("userId") int userId,
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("searchText") String searchText
    );

    public int getTeacherStudyMypageCount(
            @Param("userId") int userId,
            @Param("count") int count,
            @Param("searchText") String searchText
    );

    public int updateImgUrl(User user);

    public int declareUser(DeclareUser declareUser);

}
