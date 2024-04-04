package com.meeteam4.meeting.controller;


import com.meeteam4.meeting.dto.SignupStudentReqDto;
import com.meeteam4.meeting.dto.SignupTeacherReqDto;
import com.meeteam4.meeting.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth/signup")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/student")
    public ResponseEntity<?> signupStudent (@RequestBody SignupStudentReqDto signupStudentReqDto) {
        return ResponseEntity.ok(authService.signupStudent(signupStudentReqDto));
    }

    @PostMapping("/teacher")
    public ResponseEntity<?> signupTeacher (@RequestBody SignupTeacherReqDto signupTeacherReqDto) {
        return ResponseEntity.ok(authService.signupTeacher(signupTeacherReqDto));
    }


}
