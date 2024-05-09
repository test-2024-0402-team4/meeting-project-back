package com.meeteam4.meeting.service;


import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.entity.*;
import com.meeteam4.meeting.exception.SaveException;
import com.meeteam4.meeting.repository.AccountMapper;
import com.meeteam4.meeting.security.PrincipalStudent;
import com.meeteam4.meeting.security.PrincipalUser;
import com.meeteam4.meeting.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private UserMapper userMapper;

    // 선생님 필수 정보 등록
    @Transactional(rollbackFor = Exception.class)
    public void saveTeacherProfile(TeacherProfileReqDto teacherProfileReqDto) {
        DateRegister dateRegister = DateRegister.builder()
                .userId(teacherProfileReqDto.getUserId())
                .dateIds(teacherProfileReqDto.getDateIds())
                .build();

        SubjectRegister subjectRegister = SubjectRegister
                .builder()
                .userId(teacherProfileReqDto.getUserId())
                .subjectIds(teacherProfileReqDto.getSubjectIds())
                .build();

        RegionRegister regionRegister = RegionRegister
                .builder()
                .userId(teacherProfileReqDto.getUserId())
                .regionIds(teacherProfileReqDto.getRegionIds())
                .build();

        ClassTypeRegister classTypeRegister = ClassTypeRegister
                .builder()
                .userId(teacherProfileReqDto.getUserId())
                .classTypeIds(teacherProfileReqDto.getClassTypeIds())
                .build();

        TeacherIntroduce teacherIntroduce = TeacherIntroduce
                .builder()
                .userId(teacherProfileReqDto.getUserId())
                .teacherIntroduceContent(teacherProfileReqDto.getTeacherIntroduceContent())
                .build();

        int successCount = 0;
        successCount += accountMapper.saveDates(dateRegister);
        successCount += accountMapper.saveRegions(regionRegister);
        successCount += accountMapper.saveSubjects(subjectRegister);
        successCount += accountMapper.saveClassType(classTypeRegister);
        successCount += accountMapper.saveTeacherIntroduce(teacherIntroduce);

        if (successCount < 5) {
            throw new SaveException();
        }

    }
    // 선생님 기본 프로필 정보 수정
    @Transactional(rollbackFor = Exception.class)
    public void modifyTeacherProfile(TeacherProfileModifyDto teacherProfileModifyDto) {

        User user = teacherProfileModifyDto.toUserEntity();
        Teacher teacher = teacherProfileModifyDto.toTeacherEntity();

        accountMapper.modifyUserProfile(user);
        accountMapper.modifyTeacherProfile(teacher);
    }

    // 선생님 필수정보 수정
    @Transactional(rollbackFor = Exception.class)
    public void modifyTeacherEssentialInfo(TeacherProfileReqDto teacherProfileReqDto){

        DateRegister dateRegister = DateRegister.builder()
                .userId(teacherProfileReqDto.getUserId())
                .dateIds(teacherProfileReqDto.getDateIds())
                .build();

        SubjectRegister subjectRegister = SubjectRegister
                .builder()
                .userId(teacherProfileReqDto.getUserId())
                .subjectIds(teacherProfileReqDto.getSubjectIds())
                .build();

        RegionRegister regionRegister = RegionRegister
                .builder()
                .userId(teacherProfileReqDto.getUserId())
                .regionIds(teacherProfileReqDto.getRegionIds())
                .build();

        ClassTypeRegister classTypeRegister = ClassTypeRegister
                .builder()
                .userId(teacherProfileReqDto.getUserId())
                .classTypeIds(teacherProfileReqDto.getClassTypeIds())
                .build();

        TeacherIntroduce teacherIntroduce = TeacherIntroduce
                .builder()
                .userId(teacherProfileReqDto.getUserId())
                .teacherIntroduceContent(teacherProfileReqDto.getTeacherIntroduceContent())
                .build();

        int successCount = 0;

        //삭제 후
        successCount += accountMapper.deleteDates(teacherProfileReqDto.getUserId());
        successCount += accountMapper.deleteRegions(teacherProfileReqDto.getUserId());
        successCount += accountMapper.deleteSubjects(teacherProfileReqDto.getUserId());
        successCount += accountMapper.deleteClassTypes(teacherProfileReqDto.getUserId());
        successCount += accountMapper.deleteTeacherIntroduce(teacherProfileReqDto.getUserId());

        //다시 등록
        successCount += accountMapper.saveDates(dateRegister);
        successCount += accountMapper.saveRegions(regionRegister);
        successCount += accountMapper.saveSubjects(subjectRegister);
        successCount += accountMapper.saveClassType(classTypeRegister);
        successCount += accountMapper.saveTeacherIntroduce(teacherIntroduce);

        if (successCount < 10) {
            throw new SaveException();
        }
    }

    // 학생 프로필 수정
    @Transactional(rollbackFor = Exception.class)
    public void modifyStudentProfile(StudentProfileModifyDto studentProfileModifyDto) {
        User user = studentProfileModifyDto.toUserEntity();
        Student student = studentProfileModifyDto.toStudentEntity();
        accountMapper.modifyUserProfile(user);
        accountMapper.modifyStudentProfile(student);
    }
    // 학생 공고 포스터 등록
    @Transactional(rollbackFor = Exception.class)
    public int saveStudentPoster(PosterReqDto posterReqDto) {
        int successCount = 0;
        Poster poster = posterReqDto.toEntity();

        successCount += accountMapper.saveStudentPoster(poster);
        successCount += accountMapper.savePosterDate(posterReqDto.toPosterDateRegisterEntity(poster.getPosterId()));
        successCount += accountMapper.savePosterSubjectIds(posterReqDto.toPosterSubjectRegisterEntity(poster.getPosterId()));
        successCount += accountMapper.savePosterClassTypeIds(posterReqDto.toPosterClassTypeRegisterEntity(poster.getPosterId()));

        if(successCount < 4 ){
            throw new SaveException();
        }
        return successCount;
    }
    // 학생 올린 공고 포스터 수정
    @Transactional(rollbackFor = Exception.class)
    public int modifyStudentPoster(PosterReqDto posterReqDto) {
        int successCount = 0;

        Poster poster = posterReqDto.toEntity();

        // 삭제
        successCount += accountMapper.deletePosterDate(posterReqDto.getPosterId());
        successCount += accountMapper.deletePosterSubjectIds(posterReqDto.getPosterId());
        successCount += accountMapper.deletePosterClassTypeIds(posterReqDto.getPosterId());

        //다시 등록
        successCount += accountMapper.savePosterDate(posterReqDto.toPosterDateRegisterEntity(poster.getPosterId()));
        successCount += accountMapper.savePosterSubjectIds(posterReqDto.toPosterSubjectRegisterEntity(poster.getPosterId()));
        successCount += accountMapper.savePosterClassTypeIds(posterReqDto.toPosterClassTypeRegisterEntity(poster.getPosterId()));

        //poster_tb 은 수정
        successCount += accountMapper.modifyStudentPoster(poster);

        if(successCount < 7 ){
            throw new SaveException();
        }
        return successCount;
    }
    // 학생(본인) 공고포스터 삭제
    public int deleteStudentPoster(int posterId) {
        int successCount = 0;

        successCount += accountMapper.deleteStudentPoster(posterId);
        successCount += accountMapper.deletePosterDate(posterId);
        successCount += accountMapper.deletePosterSubjectIds(posterId);
        successCount += accountMapper.deletePosterClassTypeIds(posterId);

        if(successCount < 4 ){
            throw new SaveException();
        }
        return successCount;
    }

    // 마이페이지 내가 쓴 글 조회
    public List<StudentBoardListRespDto> searchStudentMypageBoards(StudentBoardListReqDto studentBoardListReqDto){

        int startIndex = (studentBoardListReqDto.getPage()-1)* studentBoardListReqDto.getCount();

        List<StudentBoard> boards = accountMapper.searchStudentMypageBoards(
                studentBoardListReqDto.getUserId(),
                startIndex,
                studentBoardListReqDto.getCount(),
                studentBoardListReqDto.getSearchText()
        );
        return boards.stream().map(StudentBoard :: toStudentBoardListRespDto).collect(Collectors.toList());
    }
    // 마이페이지 내가 쓴 글 페이지 조회
    public StudentCountRespDto getStudentMypageCount(StudentBoardListReqDto studentBoardListReqDto){
        int studentCount = accountMapper.getStudentMypageCount(
                studentBoardListReqDto.getUserId(),
                studentBoardListReqDto.getCount(),
                studentBoardListReqDto.getSearchText()
        );

        int maxPageNumber = (int) Math.ceil(((double) studentCount) / studentBoardListReqDto.getCount());

        return StudentCountRespDto.builder()
                .totalCount(studentCount)
                .maxPageNumber(maxPageNumber)
                .build();
    }
    public List<TeacherBoardListRespDto> searchTeacherMypageBoards(TeacherBoardListReqDto teacherBoardListReqDto){

        int startIndex = (teacherBoardListReqDto.getPage()-1)* teacherBoardListReqDto.getCount();

        List<TeacherBoard> boards = accountMapper.searchTeacherMypageBoards(
                teacherBoardListReqDto.getUserId(),
                startIndex,
                teacherBoardListReqDto.getCount(),
                teacherBoardListReqDto.getSearchText()
        );
        return boards.stream().map(TeacherBoard :: toTeacherBoardListRespDto).collect(Collectors.toList());
    }
    public TeacherCountRespDto getTeacherMypageCount(TeacherBoardListReqDto teacherBoardListReqDto){
        int teacherCount = accountMapper.getTeacherMypageCount(
                teacherBoardListReqDto.getUserId(),
                teacherBoardListReqDto.getCount(),
                teacherBoardListReqDto.getSearchText()
        );
        int maxPageNumber = (int) Math.ceil(((double) teacherCount) / teacherBoardListReqDto.getCount());
        return TeacherCountRespDto.builder()
                .totalCount(teacherCount)
                .maxPageNumber(maxPageNumber)
                .build();
    }

    public List<StudyBoardListRespDto> searchStudyMypageBoards(StudyBoardListReqDto studyBoardListReqDto){
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        int startIndex = (studyBoardListReqDto.getPage()-1)* studyBoardListReqDto.getCount();

        List<StudyBoard> boards = accountMapper.searchStudyMypageBoards(
                principalUser.getUserId(),
                startIndex,
                studyBoardListReqDto.getCount(),
                studyBoardListReqDto.getSearchText()
        );

        return boards.stream().map(StudyBoard :: toStudyBoardListRespDto).collect(Collectors.toList());
    }
    public StudyCountRespDto getStudyMypageCount(StudyBoardListReqDto studyBoardListReqDto){
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        int studyCount = accountMapper.getStudentMypageCount(
                principalUser.getUserId(),
                studyBoardListReqDto.getCount(),
                studyBoardListReqDto.getSearchText()
        );

        int maxPageNumber = (int) Math.ceil(((double) studyCount) / studyBoardListReqDto.getCount());

        return StudyCountRespDto.builder()
                .totalCount(studyCount)
                .maxPageNumber(maxPageNumber)
                .build();
    }
    public List<StudyBoardListRespDto> searchTeacherStudyMypageBoards(StudyBoardListReqDto studyBoardListReqDto){
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        int startIndex = (studyBoardListReqDto.getPage()-1)* studyBoardListReqDto.getCount();

        List<StudyBoard> boards = accountMapper.searchTeacherStudyMypageBoards(
                principalUser.getUserId(),
                startIndex,
                studyBoardListReqDto.getCount(),
                studyBoardListReqDto.getSearchText()
        );
        return boards.stream().map(StudyBoard :: toStudyBoardListRespDto).collect(Collectors.toList());
    }
    public StudyCountRespDto getTeacherStudyMypageCount(StudyBoardListReqDto studyBoardListReqDto){
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int studyCount = accountMapper.getTeacherStudyMypageCount(
                principalUser.getUserId(),
                studyBoardListReqDto.getCount(),
                studyBoardListReqDto.getSearchText()
        );
        int maxPageNumber = (int) Math.ceil(((double) studyCount) / studyBoardListReqDto.getCount());
        return StudyCountRespDto.builder()
                .totalCount(studyCount)
                .maxPageNumber(maxPageNumber)
                .build();
    }
    // 프로필 이미지 등록
    public void updateImgUrl(UpdateImgUrlReqDto updateImgUrlReqDto) {
        accountMapper.updateImgUrl(updateImgUrlReqDto.toEntity());
    }
    // 과외 신청 내역 등록
    public void saveApplicationDetails(int studentUserId, int teacherUserId) {
        accountMapper.saveApplicationDetails(studentUserId, teacherUserId);
    }
    // 과외 신청 내역 조회
    public List<SearchProfilesRespDto> getApplicationDetails(int userId) {
       List<Integer> teacherUserIds = accountMapper.getUserIdByApplicationDetails(userId);
        if(teacherUserIds.isEmpty()) {
            return null;
        }
        List<User> users = accountMapper.getTeacherProfiles(teacherUserIds);
        List<SearchProfilesRespDto> searchProfiles = new ArrayList<>();

        for (User user : users) {
            // User 클래스의 toSearchProfilesRespDto 메서드를 호출하여 검색 프로필을 생성
            SearchProfilesRespDto searchProfile = user.toSearchProfilesRespDto();
            searchProfile.setUserImgUrl(user.getUserImgUrl());
            searchProfile.setDepartmentName(user.getTeacher().getDepartmentName());
            searchProfile.setUniversityName(user.getUniversity().getUniversityName());

            List<String> subjectNames = user.getSubjectRegister().stream()
                    .map(sr -> sr.getSubject().getSubjectName())
                    .distinct()
                    .collect(Collectors.toList());
            searchProfile.setSubjectNames(subjectNames);

            List<String> classTypeNames = user.getClassTypeRegister().stream()
                    .map(ctr -> ctr.getClassType().getClassType())
                    .distinct()
                    .collect(Collectors.toList());
            searchProfile.setClassTypeNames(classTypeNames);

            searchProfiles.add(searchProfile);
        }
        return searchProfiles;
    }

    public int declareUser (DeclareUserReqDto declareUserReqDto) {
        int reslut = 0;

        reslut += accountMapper.declareUser(declareUserReqDto.toDeclareUser());
        return reslut;
    }

}
