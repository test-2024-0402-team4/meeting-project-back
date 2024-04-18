package com.meeteam4.meeting.service;


import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.entity.OAuth2;
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

    // 토큰
    @Autowired
    private JwtProvider jwtProvider;

    // 비밀번호 암호화
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 회원가입
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

    // 로그인
    public String signin(SigninReqDto signinReqDto) {

        User user = userMapper.findByUsername(signinReqDto.getUsername());

        if(user == null) {
            throw new UsernameNotFoundException("사용자 정보를 확인하세요.");
        }else if(!passwordEncoder.matches(signinReqDto.getPassword(), user.getPassword())) { // matches(암호화된 값을 풀어서 평문으로 만든다, 입력한 값) 두 매개변수를 비교
            throw new BadCredentialsException("사용자 정보를 확인하세요.");
        }
        return jwtProvider.generateToken(user);     // 토큰 만들기
    }

    // 소셜 회원가입
    @Transactional(rollbackFor = Exception.class)
    public void oAuth2Signup(OAuth2SignupReqDto oAuth2SignupReqDto) {

        User user = oAuth2SignupReqDto.toUserEntity(passwordEncoder);

        userMapper.saveUser(user);

        Student student = oAuth2SignupReqDto.toStudentEntity(user.getUserId());
        Teacher teacher = oAuth2SignupReqDto.toTeacherEntity(user.getUserId());
        if(user.getRoleId() == 1) {
            userMapper.saveStudent(student);
        }else if(user.getRoleId() == 2) {
            userMapper.saveTeacher(teacher);
        }
        userMapper.saveRole(user.getUserId(), user.getRoleId());
        userMapper.saveOAuth2(oAuth2SignupReqDto.toOAuth2Entity(user.getUserId()));
    }

    // 로그인 병합
    public void oAuth2Merge(OAuth2MergeReqDto oAuth2MergeReqDto) {
        User user = userMapper.findByUsername(oAuth2MergeReqDto.getUsername());

        if(user == null) {
            throw new UsernameNotFoundException("사용자 정보를 확인하세요.");
        }else if(!passwordEncoder.matches(oAuth2MergeReqDto.getPassword(), user.getPassword())){
            throw new BadCredentialsException("사용자 정보를 확인하세요.");
        }

        OAuth2 oAuth2 = OAuth2
                .builder()
                .userId(user.getUserId())
                .oAuth2Name(oAuth2MergeReqDto.getOAuth2Name())
                .providerName(oAuth2MergeReqDto.getProviderName())
                .build();
        userMapper.saveOAuth2(oAuth2);
    }

    // ID 찾기
    public String findId(AuthFindIdReqDto authFindIdReqDto ) {
        User user = userMapper.findByName(authFindIdReqDto.getName(), authFindIdReqDto.getEmail());
//        System.out.println(user);

        String name = user.getName();
        String username = user.getUsername();
        String email = user.getEmail();

        // 예외 처리가 안됨 -- 수정 필요
//        if(name == null) {
//            throw new BadCredentialsException("이름을 확인하세요.");
////            return "사용자 이름을 찾지 못했습니다.";
//        }else if(email == null) {
//            throw new BadCredentialsException("이메일을 확인하세요.");
////            return "이메일을 찾지 못했습니다.";
//        }
        return username;
    }

    public int findPassword(AuthFindPasswordReqDto authFindPasswordReqDto) {
        User user = userMapper.findPassword(authFindPasswordReqDto.getUsername(), authFindPasswordReqDto.getEmail());

        System.out.println(user);

        String name = user.getName();
        String username = user.getUsername();
        String email = user.getEmail();

        System.out.println(username);
        System.out.println(email);

//        if(username == null) {
//            throw new UsernameNotFoundException("아이디를 확인하세요.");
//        }else if(email == null) {
//            throw new BadCredentialsException("이메일을 확인하세요.");
//        }

        return 1;
    }

}
