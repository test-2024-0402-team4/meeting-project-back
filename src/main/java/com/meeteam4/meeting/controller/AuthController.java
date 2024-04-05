package com.meeteam4.meeting.controller;


import com.meeteam4.meeting.aop.annotation.ValidAspect;
import com.meeteam4.meeting.dto.SignupStudentReqDto;
import com.meeteam4.meeting.dto.SignupTeacherReqDto;
import com.meeteam4.meeting.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth/signup")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ValidAspect
    @PostMapping("/student")
    public ResponseEntity<?> signupStudent (@Valid @RequestBody SignupStudentReqDto signupStudentReqDto, BindingResult bindingResult) {
        return ResponseEntity.ok(authService.signupStudent(signupStudentReqDto));
    }

    @ValidAspect
    @PostMapping("/teacher")
    public ResponseEntity<?> signupTeacher (@Valid @RequestBody SignupTeacherReqDto signupTeacherReqDto, BindingResult bindingResult) {
        return ResponseEntity.ok(authService.signupTeacher(signupTeacherReqDto));
    }


}
