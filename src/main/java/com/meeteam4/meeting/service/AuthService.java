package com.meeteam4.meeting.service;


import com.meeteam4.meeting.dto.SignupUserDto;
import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.entity.Teacher;
import com.meeteam4.meeting.entity.User;
import com.meeteam4.meeting.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public void signupUser(SignupUserDto signupUserDto) {

        User user = signupUserDto.toUserEntity(passwordEncoder);
        Student student = signupUserDto.toStudentEntity();
        Teacher teacher = signupUserDto.toTeacherEntity();

        userMapper.saveUser(user);

        if(user.getRoleId() == 1) {
            userMapper.saveStudent(student);
        }else if(user.getRoleId() == 2) {
            userMapper.saveTeacher(teacher);
        }

    }

}
