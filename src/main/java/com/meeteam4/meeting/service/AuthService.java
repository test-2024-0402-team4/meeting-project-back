package com.meeteam4.meeting.service;


import com.meeteam4.meeting.dto.SignupStudentReqDto;
import com.meeteam4.meeting.dto.SignupTeacherReqDto;
import com.meeteam4.meeting.dto.SignupUserDto;
import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.entity.Teacher;
import com.meeteam4.meeting.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public void signup(SignupUserDto signupUserDto, SignupTeacherReqDto signupTeacherReqDto, SignupStudentReqDto signupStudentReqDto) {

        User user = signupUserDto.toEntity(passwordEncoder);
        Teacher teacher = signupTeacherReqDto.toEntity();
        Student student = signupStudentReqDto.toEntity();

        if(user.getRoleId() == 1) {

        }else if(user.getRoleId() == 2) {

        }
    }

}
