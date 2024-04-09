package com.meeteam4.meeting.controller;


import com.meeteam4.meeting.dto.PosterReqDto;
import com.meeteam4.meeting.dto.TeacherProfileReqDto;
import com.meeteam4.meeting.entity.Poster;
import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.security.PrincipalStudent;
import com.meeteam4.meeting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {


    @Autowired
    private AccountService accountService;



    @GetMapping("/mypage")
    public ResponseEntity<?> getStudentId(@RequestParam int userId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication.getPrincipal() instanceof PrincipalStudent) {
//            PrincipalStudent principalStudent = (PrincipalStudent) authentication.getPrincipal();
//            int studentId = principalStudent.g; // 아이디 값을 가져옴
//            return ResponseEntity.ok(null);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
        return ResponseEntity.ok(accountService.getStudentInfo(userId));
    }

    @PostMapping("/teacher/profile")
    public ResponseEntity<?> saveTeacherProfile(@RequestBody TeacherProfileReqDto teacherProfileReqDto) {

        accountService.saveTeacherProfile(teacherProfileReqDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/teacher/profiles")
    public ResponseEntity<?> searchTeacherProfilesByUserId(int userId) {



        return ResponseEntity.ok(null);
    }
}