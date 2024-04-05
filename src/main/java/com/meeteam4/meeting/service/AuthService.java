package com.meeteam4.meeting.service;


import com.meeteam4.meeting.dto.SignupStudentReqDto;
import com.meeteam4.meeting.dto.SignupTeacherReqDto;
import com.meeteam4.meeting.entity.Teacher;
import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public int signupStudent(SignupStudentReqDto signupStudentReqDto) {
        int successCount = 0;

        Student student = signupStudentReqDto.toEntity(passwordEncoder);

        successCount = userMapper.saveStudent(student);

        return successCount;
    }

    public int signupTeacher(SignupTeacherReqDto signupTeacherReqDto) {
        int successCount = 0;

        Teacher teacher = signupTeacherReqDto.toEntity(passwordEncoder);

        successCount = userMapper.saveTeacher(teacher);

        return successCount;
    }
}
