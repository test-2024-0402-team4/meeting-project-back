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

        if(successCount < 4) {
            throw new SaveException();
        }

    }
    // 검색 필터
    public List<SearchProfilesRespDto> searchTeacherProfiles(SearchProfilesReqDto searchProfilesReqDto) {
        List<Integer> userIds = new ArrayList<>();

        userIds.addAll(accountMapper.searchUserIdByRegionIds(searchProfilesReqDto.getRegionIds()));
        userIds.addAll(accountMapper.searchUserIdBySubjectIds(searchProfilesReqDto.getSubjectIds()));
        userIds.addAll(accountMapper.searchUserIdByDateIds(searchProfilesReqDto.getDateIds()));
        userIds.addAll(accountMapper.searchUserIdByClassTypeIds(searchProfilesReqDto.getClassTypeIds()));

        // DB에서 가져온 userId 중복 제거
        List<Integer> distinctUserIds = userIds.stream().distinct().collect(Collectors.toList());


        List<User> users = accountMapper.searchTeacherProfilesByUserId(distinctUserIds);


        return users.stream().map(User :: toSearchProfilesRespDto).collect(Collectors.toList());
    }


}
