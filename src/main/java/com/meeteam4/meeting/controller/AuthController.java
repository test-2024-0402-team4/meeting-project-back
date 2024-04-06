package com.meeteam4.meeting.controller;


import com.meeteam4.meeting.dto.SignupStudentReqDto;
import com.meeteam4.meeting.dto.SignupTeacherReqDto;
import com.meeteam4.meeting.dto.SignupUserDto;
import com.meeteam4.meeting.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signup (
            @Valid
            @RequestBody SignupUserDto signupUserDto,
            SignupStudentReqDto signupStudentReqDto,
            SignupTeacherReqDto signupTeacherReqDto,
            BindingResult bindingResult) {

        authService.signup(signupUserDto, signupTeacherReqDto, signupStudentReqDto);

        return ResponseEntity.created(null).body(null);
    }

}
