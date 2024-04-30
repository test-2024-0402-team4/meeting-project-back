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

    public UserDataRespDto getStudentInfo(int userId) {

        UserDataRespDto userDataRespDto = UserDataRespDto
                .builder()
                .nickname(accountMapper.findStudentByStudentId(userId).getUser().getNickname())
                .email(accountMapper.findStudentByStudentId(userId).getUser().getEmail())
                .studentType(accountMapper.findStudentByStudentId(userId).getStudentType().getStudentType())
                .genderType(accountMapper.findStudentByStudentId(userId).getGender().getGenderType())
                .regionName(accountMapper.findStudentByStudentId(userId).getRegion().getRegionName())
                .build();

       return userDataRespDto;
    }

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
        int successCount = 0;
        successCount += accountMapper.saveDates(dateRegister);
        successCount += accountMapper.saveRegions(regionRegister);
        successCount += accountMapper.saveSubjects(subjectRegister);
        successCount += accountMapper.saveClassType(classTypeRegister);

        if (successCount < 4) {
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


    // 검색 필터
    @Transactional(rollbackFor = Exception.class)
    public List<SearchProfilesRespDto> searchTeacherProfiles(SearchProfilesReqDto searchProfilesReqDto) {

        List<Integer> userIds = new ArrayList<>();
        if(searchProfilesReqDto.getGenderId() == null) {
            searchProfilesReqDto.setGenderId(0);
        }
    // 검색 조건에 맞는 UserIds
        userIds.addAll(accountMapper.searchUserIds(
                    searchProfilesReqDto.getNickname(),
                    searchProfilesReqDto.getGenderId(),
                    searchProfilesReqDto.getRegionIds(),
                    searchProfilesReqDto.getSubjectIds(),
                    searchProfilesReqDto.getClassTypeIds(),
                    searchProfilesReqDto.getDateIds())
        );


        // DB에서 가져온 userIds 중복 제거
        List<Integer> distinctUserIds = userIds.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctUserIds);

        if(distinctUserIds.isEmpty()) {
            return null;
        }

        List<User> users = accountMapper.getTeacherProfiles(distinctUserIds);
        List<SearchProfilesRespDto> searchProfiles = new ArrayList<>();

        for (User user : users) {
            // User 클래스의 toSearchProfilesRespDto 메서드를 호출하여 검색 프로필을 생성
            SearchProfilesRespDto searchProfile = user.toSearchProfilesRespDto();

                searchProfile.setUserImgUrl(user.getUserImgUrl().getUserImgUrl());
            // Teacher 처리
                searchProfile.setDepartmentName(user.getTeacher().getDepartmentName());
            // GraduateState 처리
                searchProfile.setGraduateState(user.getGraduateState().getGraduateState());
            // Gender 처리
                searchProfile.setGenderType(user.getGender().getGenderType());
            // University 처리
                searchProfile.setUniversityName(user.getUniversity().getUniversityName());

            // 과목 등록 정보 처리
            List<String> subjectNames = user.getSubjectRegister().stream()
                    .map(sr -> sr.getSubject().getSubjectName())
                    .distinct()
                    .collect(Collectors.toList());
            searchProfile.setSubjectNames(subjectNames);

            // 수업 유형 등록 정보 처리
            List<String> classTypeNames = user.getClassTypeRegister().stream()
                    .map(ctr -> ctr.getClassType().getClassType())
                    .distinct()
                    .collect(Collectors.toList());
            searchProfile.setClassTypeNames(classTypeNames);

            // 날짜 등록 정보 처리
            List<String> dateNames = user.getDateRegister().stream()
                    .map(dr -> dr.getDate().getDateType())
                    .distinct()
                    .collect(Collectors.toList());
            searchProfile.setDateNames(dateNames);

            // 지역 등록 정보 처리
            List<String> regionNames = user.getRegionRegister().stream()
                    .map(rr -> rr.getRegion().getRegionName())
                    .distinct()
                    .collect(Collectors.toList());
            searchProfile.setRegionNames(regionNames);

            // 생성된 검색 프로필을 리스트에 추가
            searchProfiles.add(searchProfile);
        }

        return searchProfiles;
    }
    // 단건 프로필 조회
    @Transactional(rollbackFor = Exception.class)
    public SearchProfilesRespDto getTeacherProfileRespDto(Integer userId) {
        List<Integer> userIds = new ArrayList<>();
        userIds.add(userId);

        List<User> users = accountMapper.getTeacherProfiles(userIds);
        if(userIds.isEmpty()) {
            return null;
        }
        List<SearchProfilesRespDto> searchProfiles = new ArrayList<>();

        for (User user : users) {
            // User 클래스의 toSearchProfilesRespDto 메서드를 호출하여 검색 프로필을 생성
            SearchProfilesRespDto searchProfile = user.toSearchProfilesRespDto();

            searchProfile.setUserImgUrl(user.getUserImgUrl().getUserImgUrl());
            // Teacher 처리
            searchProfile.setDepartmentName(user.getTeacher().getDepartmentName());
            searchProfile.setBirthDate(user.getTeacher().getBirthDate());
            // GraduateState 처리
            searchProfile.setGraduateState(user.getGraduateState().getGraduateState());
            // Gender 처리
            searchProfile.setGenderType(user.getGender().getGenderType());
            // University 처리
            searchProfile.setUniversityName(user.getUniversity().getUniversityName());

            // 과목 등록 정보 처리
            List<String> subjectNames = user.getSubjectRegister().stream()
                    .map(sr -> sr.getSubject().getSubjectName())
                    .distinct()
                    .collect(Collectors.toList());
            searchProfile.setSubjectNames(subjectNames);

            // 수업 유형 등록 정보 처리
            List<String> classTypeNames = user.getClassTypeRegister().stream()
                    .map(ctr -> ctr.getClassType().getClassType())
                    .distinct()
                    .collect(Collectors.toList());
            searchProfile.setClassTypeNames(classTypeNames);

            // 날짜 등록 정보 처리
            List<String> dateNames = user.getDateRegister().stream()
                    .map(dr -> dr.getDate().getDateType())
                    .distinct()
                    .collect(Collectors.toList());
            searchProfile.setDateNames(dateNames);

            // 지역 등록 정보 처리
            List<String> regionNames = user.getRegionRegister().stream()
                    .map(rr -> rr.getRegion().getRegionName())
                    .distinct()
                    .collect(Collectors.toList());
            searchProfile.setRegionNames(regionNames);

            // 생성된 검색 프로필을 리스트에 추가
            searchProfiles.add(searchProfile);
        }
        return searchProfiles.get(0);
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


    // 학생 공고 조회
    @Transactional(rollbackFor = Exception.class)
    public List<SearchPosterRespDto> getStudentPosters(SearchPosterReqDto searchPosterReqDto) {
        List<Integer> posterIds = new ArrayList<>();

        System.out.println(searchPosterReqDto);

        posterIds.addAll(accountMapper.searchPosterIds(
                searchPosterReqDto.getRegionIds(),
                searchPosterReqDto.getSubjectIds(),
                searchPosterReqDto.getDateIds(),
                searchPosterReqDto.getStudentTypeIds()));
        System.out.println(posterIds);

        if(posterIds.isEmpty()) {
            return null;
        }
        List<Poster> posters = accountMapper.getPosters(posterIds);
        System.out.println(posters);
        List<SearchPosterRespDto> searchPosters = new ArrayList<>();

        for (Poster poster : posters) {
            SearchPosterRespDto searchPosterRespDto = poster.toSearchPosterRespDto();
            searchPosterRespDto.setPosterId(poster.getPosterId());
            searchPosterRespDto.setGenderType(poster.getGender().getGenderType());
            searchPosterRespDto.setStudentType(poster.getStudentType().getStudentType());
            searchPosterRespDto.setRegionName(poster.getRegion().getRegionName());

            List<String> subjectNames = poster.getPosterSubjectRegister().stream()
                    .map(ps -> ps.getSubject().getSubjectName())
                    .distinct()
                    .collect(Collectors.toList());

            searchPosterRespDto.setSubjectName(subjectNames);
            System.out.println(subjectNames);

            List<String> dateTypes = poster.getPosterDateRegister().stream()
                    .map(pd -> pd.getDate().getDateType())
                    .distinct()
                    .collect(Collectors.toList());
            searchPosterRespDto.setDateType(dateTypes);
            System.out.println(dateTypes);

            List<String> classTypes = poster.getPosterClassTypeRegister().stream()
                    .map(pct -> pct.getClassType().getClassType())
                    .distinct()
                    .collect(Collectors.toList());
            searchPosterRespDto.setClassType(classTypes);

            searchPosters.add(searchPosterRespDto);

        }
        return searchPosters;
    }

    public SearchPosterRespDto getStudentPoster(int posterId) {

        Poster poster = accountMapper.getPoster(posterId);
        System.out.println(poster);

        SearchPosterRespDto searchPosterRespDto = poster.toSearchPosterRespDto();
        searchPosterRespDto.setPosterId(poster.getPosterId());
        searchPosterRespDto.setGenderType(poster.getGender().getGenderType());
        searchPosterRespDto.setStudentType(poster.getStudentType().getStudentType());
        searchPosterRespDto.setRegionName(poster.getRegion().getRegionName());

        List<String> subjectNames = poster.getPosterSubjectRegister().stream()
                .map(ps -> ps.getSubject().getSubjectName())
                .distinct()
                .collect(Collectors.toList());

        searchPosterRespDto.setSubjectName(subjectNames);
        System.out.println(subjectNames);

        List<String> dateTypes = poster.getPosterDateRegister().stream()
                .map(pd -> pd.getDate().getDateType())
                .distinct()
                .collect(Collectors.toList());
        searchPosterRespDto.setDateType(dateTypes);
        System.out.println(dateTypes);

        List<String> classTypes = poster.getPosterClassTypeRegister().stream()
                .map(pct -> pct.getClassType().getClassType())
                .distinct()
                .collect(Collectors.toList());
        searchPosterRespDto.setClassType(classTypes);

        return searchPosterRespDto;

    }

    public void saveImgUrl(ImgUrlSaveReqDto urlSaveReqDto){
        System.out.println(urlSaveReqDto);
        accountMapper.saveImgUrl(urlSaveReqDto.toEntity());
    }

    public StudentProfileRespDto getStudentProfile(int userId){
        if(userId == 0) {
            return null;
        }
        User studentProfile = accountMapper.getStudentProfile(userId);

        System.out.println(studentProfile);

        return studentProfile.toStudentProfileRespDto();
    }

    public List<SearchPosterRespDto> getStudentMyPosters(int userId) {

        List<Poster> posters = accountMapper.getStudentMyPosters(userId);
        System.out.println(posters);
        List<SearchPosterRespDto> searchPosters = new ArrayList<>();

        for (Poster poster : posters) {
            SearchPosterRespDto searchPosterRespDto = poster.toSearchPosterRespDto();
            searchPosterRespDto.setPosterId(poster.getPosterId());
            searchPosterRespDto.setGenderType(poster.getGender().getGenderType());
            searchPosterRespDto.setStudentType(poster.getStudentType().getStudentType());
            searchPosterRespDto.setRegionName(poster.getRegion().getRegionName());

            List<String> subjectNames = poster.getPosterSubjectRegister().stream()
                    .map(ps -> ps.getSubject().getSubjectName())
                    .distinct()
                    .collect(Collectors.toList());

            searchPosterRespDto.setSubjectName(subjectNames);
            System.out.println(subjectNames);

            List<String> dateTypes = poster.getPosterDateRegister().stream()
                    .map(pd -> pd.getDate().getDateType())
                    .distinct()
                    .collect(Collectors.toList());
            searchPosterRespDto.setDateType(dateTypes);
            System.out.println(dateTypes);

            List<String> classTypes = poster.getPosterClassTypeRegister().stream()
                    .map(pct -> pct.getClassType().getClassType())
                    .distinct()
                    .collect(Collectors.toList());
            searchPosterRespDto.setClassType(classTypes);

            searchPosters.add(searchPosterRespDto);
        }
        return searchPosters;
    }
    public SearchPosterRespDto getMyposter(int posterId) {
        Poster poster = accountMapper.getStudentMyPoster(posterId);
        SearchPosterRespDto searchPosterRespDto = poster.toSearchPosterRespDto();
        searchPosterRespDto.setPosterId(poster.getPosterId());
        searchPosterRespDto.setGenderType(poster.getGender().getGenderType());
        searchPosterRespDto.setStudentType(poster.getStudentType().getStudentType());
        searchPosterRespDto.setRegionName(poster.getRegion().getRegionName());

        List<String> subjectNames = poster.getPosterSubjectRegister().stream()
                .map(ps -> ps.getSubject().getSubjectName())
                .distinct()
                .collect(Collectors.toList());

        searchPosterRespDto.setSubjectName(subjectNames);
        System.out.println(subjectNames);

        List<String> dateTypes = poster.getPosterDateRegister().stream()
                .map(pd -> pd.getDate().getDateType())
                .distinct()
                .collect(Collectors.toList());
        searchPosterRespDto.setDateType(dateTypes);
        System.out.println(dateTypes);

        List<String> classTypes = poster.getPosterClassTypeRegister().stream()
                .map(pct -> pct.getClassType().getClassType())
                .distinct()
                .collect(Collectors.toList());
        searchPosterRespDto.setClassType(classTypes);

        return searchPosterRespDto;
    }

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

}
