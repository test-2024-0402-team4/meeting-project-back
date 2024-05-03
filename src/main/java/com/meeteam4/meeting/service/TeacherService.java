package com.meeteam4.meeting.service;


import com.meeteam4.meeting.dto.SearchProfilesRespDto;
import com.meeteam4.meeting.entity.*;
import com.meeteam4.meeting.repository.AccountMapper;
import com.meeteam4.meeting.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public SearchProfilesRespDto getTeacherProfileRespDto(Integer userId) {
        List<Integer> userIds = new ArrayList<>();
        userIds.add(userId);
        System.out.println(userId);

        List<User> users = accountMapper.getTeacherProfiles(userIds);
        System.out.println(users);

        if(users.isEmpty()) {
            return null;
        }
        SearchProfilesRespDto searchProfile = null;
        for (User user : users) {
            searchProfile = user.toSearchProfilesRespDto();
            searchProfile.setUserImgUrl(user.getUserImgUrl());
            searchProfile.setDepartmentName(user.getTeacher().getDepartmentName());
            searchProfile.setBirthDate(user.getTeacher().getBirthDate());
            searchProfile.setGraduateState(user.getGraduateState().getGraduateState());
            searchProfile.setGenderType(user.getGender().getGenderType());
            searchProfile.setUniversityName(user.getUniversity().getUniversityName());
            searchProfile.setTeacherIntroduceContent(user.getTeacherIntroduce().getTeacherIntroduceContent());

            // SubjectRegister 처리
            if (user.getSubjectRegister() != null) {
                List<String> subjectNames = user.getSubjectRegister().stream()
                        .filter(sr -> sr.getSubject() != null && sr.getSubject().getSubjectName() != null)
                        .map(sr -> sr.getSubject().getSubjectName())
                        .distinct()
                        .collect(Collectors.toList());

                searchProfile.setSubjectNames(subjectNames);
            }
            // ClassTypeRegister 처리
            if (user.getClassTypeRegister() != null) {
                List<String> classTypeNames = user.getClassTypeRegister().stream()
                        .filter(ctr -> ctr.getClassType() != null && ctr.getClassType().getClassType() != null)
                        .map(ctr -> ctr.getClassType().getClassType())
                        .distinct()
                        .collect(Collectors.toList());

                searchProfile.setClassTypeNames(classTypeNames);
            }

            // DateRegister 처리
            if (user.getDateRegister() != null) {
                List<String> dateNames = user.getDateRegister().stream()
                        .filter(dr -> dr.getDate() != null && dr.getDate().getDateType() != null)
                        .map(dr -> dr.getDate().getDateType())
                        .distinct()
                        .collect(Collectors.toList());

                searchProfile.setDateNames(dateNames);
            }

            // RegionRegister 처리
            if (user.getRegionRegister() != null) {
                List<String> regionNames = user.getRegionRegister().stream()
                        .filter(rr -> rr.getRegion() != null && rr.getRegion().getRegionName() != null)
                        .map(rr -> rr.getRegion().getRegionName())
                        .distinct()
                        .collect(Collectors.toList());

                searchProfile.setRegionNames(regionNames);
            }
        }
        return searchProfile;
    }

}
