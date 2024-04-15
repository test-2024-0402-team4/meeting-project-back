package com.meeteam4.meeting.service;


import com.meeteam4.meeting.dto.SigninReqDto;
import com.meeteam4.meeting.dto.SignupUserDto;
import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.entity.Teacher;
import com.meeteam4.meeting.entity.User;
import com.meeteam4.meeting.jwt.JwtProvider;
import com.meeteam4.meeting.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtProvider jwtProvider;

    // 비밀번호 암호화
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public void signupUser(SignupUserDto signupUserDto) {

        User user = signupUserDto.toUserEntity(passwordEncoder);
        userMapper.saveUser(user);

        Student student = signupUserDto.toStudentEntity(user.getUserId());
        Teacher teacher = signupUserDto.toTeacherEntity(user.getUserId());
        if(user.getRoleId() == 1) {
            userMapper.saveStudent(student);
        }else if(user.getRoleId() == 2) {
            userMapper.saveTeacher(teacher);
        }
        userMapper.saveRole(user.getUserId(), user.getRoleId());
    }

    public String signin(SigninReqDto signinReqDto) {

        User user = userMapper.findByUsername(signinReqDto.getUsername());

        if(user == null) {
            throw new UsernameNotFoundException("사용자 정보를 확인하세요.");
        }if(!passwordEncoder.matches(signinReqDto.getPassword(), user.getPassword())) { // matches(암호화된 값을 풀어서 평문으로 만든다, 입력한 값) 두 매개변수를 비교
            throw new BadCredentialsException("사용자 정보를 확인하세요.");
        }
        // 토큰을 만들겠다
        return jwtProvider.generateToken(user);
    }

}
