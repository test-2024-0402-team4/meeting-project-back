package com.meeteam4.meeting.controller;


import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.security.PrincipalStudent;
import com.meeteam4.meeting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {


    @Autowired
    private AccountService accountService;



    @GetMapping("/studentId")
    public ResponseEntity<?> getStudentId(@RequestParam int studentId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication.getPrincipal() instanceof PrincipalStudent) {
//            PrincipalStudent principalStudent = (PrincipalStudent) authentication.getPrincipal();
//            int studentId = principalStudent.g; // 아이디 값을 가져옴
//
//            return ResponseEntity.ok(null);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
        return ResponseEntity.ok(accountService.getStudentInfo(studentId));
    }
}