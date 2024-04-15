package com.meeteam4.meeting.controller;


import com.meeteam4.meeting.aop.annotation.ValidAspect;
import com.meeteam4.meeting.dto.SigninReqDto;
import com.meeteam4.meeting.dto.SignupUserDto;
import com.meeteam4.meeting.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ValidAspect
    @PostMapping("/signup")
    public ResponseEntity<?> signupUser (@Valid @RequestBody SignupUserDto signupUserDto, BindingResult bindingResult) {
        authService.signupUser(signupUserDto);
        return ResponseEntity.created(null).body(null);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninReqDto signinReqDto) {
        return ResponseEntity.ok(authService.signin(signinReqDto));
    }


}
