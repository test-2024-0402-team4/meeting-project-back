package com.meeteam4.meeting.service;


import com.meeteam4.meeting.dto.UserDataRespDto;
import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.repository.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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











}
