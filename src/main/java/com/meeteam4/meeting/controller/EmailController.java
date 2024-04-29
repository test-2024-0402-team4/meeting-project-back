package com.meeteam4.meeting.controller;

import com.meeteam4.meeting.dto.EmailTeacherProfileReqDto;
import com.meeteam4.meeting.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/send")
    @ResponseBody
    public ResponseEntity<?> send(@RequestBody EmailTeacherProfileReqDto teacherProfileReqDto) throws Exception {
        System.out.println(teacherProfileReqDto);
        emailService.sendTeacherProfile(teacherProfileReqDto);
        return ResponseEntity.ok(null);
    }
}
