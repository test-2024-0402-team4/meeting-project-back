package com.meeteam4.meeting.service;


import com.meeteam4.meeting.dto.PosterReqDto;
import com.meeteam4.meeting.dto.UserDataRespDto;
import com.meeteam4.meeting.entity.Poster;
import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.exception.SaveException;
import com.meeteam4.meeting.repository.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public int savePoster(PosterReqDto posterReqDto) {
        int successCount = 0;

        Poster poster = posterReqDto.toPosterEntity();
        successCount += accountMapper.savePoster(poster);
        successCount += accountMapper.saveDates(poster.getPosterId(), posterReqDto.getDateIds());

        if(successCount < 2) {
            throw new SaveException();
        }
        return successCount;
    }









}
