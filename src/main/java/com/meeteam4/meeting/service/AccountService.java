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
        System.out.println(accountMapper.getTeacherProfile(searchProfilesReqDto).get(0).getTeacher().getGenderId());
        List<User> users = accountMapper.getTeacherProfile(searchProfilesReqDto);
        SearchProfilesRespDto searchProfilesRespDto = new SearchProfilesRespDto();
        List<SearchProfilesRespDto> searchProfiles = users.stream().map(User::toSearchProfilesRespDto).collect(Collectors.toList());

        for (int i = 0; i < users.size(); i++) {
            searchProfiles.get(i).setGenderId(users.get(i).getTeacher().getGenderId());
            searchProfiles.get(i).setUniversityName(users.get(i).getUniversity().getUniversityName());
            searchProfiles.get(i).setDepartmentName(users.get(i).getTeacher().getDepartmentName());
            searchProfiles.get(i).setGraduateStateId(users.get(i).getTeacher().getGraduateStateId());
        }

        List<String> subjects = new ArrayList<>();
        List<String> subjectList = new ArrayList<>();

        List<String> classTypes = new ArrayList<>();
        List<String> classTypeList = new ArrayList<>();

        List<String> dateNames = new ArrayList<>();
        List<String> dateNameList = new ArrayList<>();

        List<String> regions = new ArrayList<>();
        List<String> regionList = new ArrayList<>();

        for (int j = 0; j < users.size(); j++) {
            List<SubjectRegister> subjectRegisters = users.get(j).getSubjectRegister();
            List<ClassTypeRegister> classTypeRegisters = users.get(j).getClassTypeRegister();
            List<DateRegister> dateRegisters = users.get(j).getDateRegister();
            List<RegionRegister> regionRegisters = users.get(j).getRegionRegister();
            System.out.println(dateRegisters);
            for (int i = 0; i < subjectRegisters.size(); i++) {
                SubjectRegister subjectRegister = subjectRegisters.get(i);
                Subject subject = subjectRegister.getSubject();
                String subjectName = subject.getSubjectName();
                subjects.add(subjectName);
            }
            subjectList = subjects.stream().distinct().collect(Collectors.toList());

            for (int i = 0; i < classTypeRegisters.size(); i++) {
                ClassTypeRegister classTypeRegister = classTypeRegisters.get(i);
                ClassType classType = classTypeRegister.getClassType();
                String classTypeName = classType.getClassType();
                classTypes.add(classTypeName);
            }
            classTypeList = classTypes.stream().distinct().collect(Collectors.toList());

            for (int i = 0; i < classTypeRegisters.size(); i++) {
                DateRegister dateRegister = dateRegisters.get(i);
                Date date = dateRegister.getDate();
                String dateName = date.getDateType();
                dateNames.add(dateName);
            }
            dateNameList = dateNames.stream().distinct().collect(Collectors.toList());

            System.out.println(regionRegisters.get(0));
            // String regionName = regionRegister.getRegion().getRegionName(); // 예시 메서드 이름입니다. 실제로는 사용 가능한 메서드를 호출해야 합니다.
            // regions.add(regionName);
            // regionList = regions.stream().distinct().collect(Collectors.toList());


            for (int i = 0; i < regionRegisters.size(); i++) {
                System.out.println(regionRegisters);
            }

        searchProfiles.get(j).setSubjectNames(subjectList);
        searchProfiles.get(j).setClassTypeNames(classTypeList);
        searchProfiles.get(j).setDateNames(dateNameList);
        searchProfiles.get(j).setRegionNames(regionList);
        }

        return searchProfiles;
    }
}
