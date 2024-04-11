package com.meeteam4.meeting.service;


import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.entity.*;
import com.meeteam4.meeting.exception.SaveException;
import com.meeteam4.meeting.repository.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.*;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    //
//    public UserDataRespDto getStudentInfo(int userId) {
//
//        UserDataRespDto userDataRespDto = UserDataRespDto
//                .builder()
//                .nickname(accountMapper.findStudentByStudentId(userId).getUser().getNickname())
//                .email(accountMapper.findStudentByStudentId(userId).getUser().getEmail())
//                .studentType(accountMapper.findStudentByStudentId(userId).getStudentType().getStudentType())
//                .genderType(accountMapper.findStudentByStudentId(userId).getGender().getGenderType())
//                .regionName(accountMapper.findStudentByStudentId(userId).getRegion().getRegionName())
//                .build();
//
//       return userDataRespDto;
//    }
    // 필수 정보 등록
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

    // 검색 필터
    // 검색 필터
    public List<SearchProfilesRespDto> searchTeacherProfiles(SearchProfilesReqDto searchProfilesReqDto) {

        List<Integer> userIds = new ArrayList<>();
        if(searchProfilesReqDto.getGenderId() == null) {
            searchProfilesReqDto.setGenderId(0);
        }

        userIds.addAll(accountMapper.searchUserIds(
                    searchProfilesReqDto.getNickname(),
                    searchProfilesReqDto.getGenderId(),
                    searchProfilesReqDto.getRegionIds(),
                    searchProfilesReqDto.getSubjectIds(),
                    searchProfilesReqDto.getClassTypeIds(),
                    searchProfilesReqDto.getDateIds())
        );


        // DB에서 가져온 userId 중복 제거
        List<Integer> distinctUserIds = userIds.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctUserIds);;
        if(distinctUserIds.size() == 0) {
            return null;
        }

        List<User> users = accountMapper.getTeacherProfile(distinctUserIds);
        List<SearchProfilesRespDto> searchProfiles = new ArrayList<>();

        for (User user : users) {
            // User 클래스의 toSearchProfilesRespDto 메서드를 호출하여 검색 프로필을 생성
            SearchProfilesRespDto searchProfile = user.toSearchProfilesRespDto();
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
}
