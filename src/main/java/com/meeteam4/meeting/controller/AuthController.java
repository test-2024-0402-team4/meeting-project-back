package com.meeteam4.meeting.controller;


import com.meeteam4.meeting.aop.annotation.ValidAspect;
import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.security.PrincipalUser;
import com.meeteam4.meeting.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // 회원가입
    @ValidAspect
    @PostMapping("/signup")
    public ResponseEntity<?> signupUser (@Valid @RequestBody SignupUserDto signupUserDto, BindingResult bindingResult) {
        authService.signupUser(signupUserDto);
        return ResponseEntity.created(null).body(true);
    }

    // 로그인 + 토큰
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninReqDto signinReqDto) {
        return ResponseEntity.ok(authService.signin(signinReqDto));
    }

    // 소셜 회원가입
    @ValidAspect
    @PostMapping("/oauth2/signup")
    public ResponseEntity<?> oAuth2Signup(@Valid @RequestBody OAuth2SignupReqDto oAuth2SignupReqDto, BindingResult bindingResult) {
        authService.oAuth2Signup(oAuth2SignupReqDto);
        return ResponseEntity.created(null).body(true);
    }

    // 소셜 회원정보 + 일반회원정보 병합
    @PostMapping("/oauth2/merge")
    public ResponseEntity<?> oAuth2merge(@RequestBody OAuth2MergeReqDto oAuth2MergeReqDto) {
        authService.oAuth2Merge(oAuth2MergeReqDto);
        return ResponseEntity.ok(true);
    }

    // 아이디 찾기
    @ValidAspect
    @GetMapping("/findId")
    public ResponseEntity<?> findId(@Valid AuthFindIdReqDto authFindIdReqDto, BindingResult bindingResult) {
        return ResponseEntity.ok(authService.findId(authFindIdReqDto));
    }

    // 비밀번호 찾기 - 확인
    @ValidAspect
    @GetMapping("/findPassword")
    public ResponseEntity<?> findPassword(@Valid AuthFindPasswordReqDto authFindPasswordReqDto, BindingResult bindingResult) {
        return ResponseEntity.ok(authService.findPassword(authFindPasswordReqDto));
    }

    // 비밀번호 찾기 - 수정
    @ValidAspect
    @PutMapping("/modifyPassword")
    public ResponseEntity<?> modifyPassword(@Valid @RequestBody ModifyPasswordReqDto modifyPasswordReqDto, BindingResult bindingResult) {
        return ResponseEntity.ok(authService.modifyPassword(modifyPasswordReqDto));
    }


    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteAccount(@PathVariable int userId) {
        authService.deleteAccount(userId);
        return ResponseEntity.ok(true);
    }





}
