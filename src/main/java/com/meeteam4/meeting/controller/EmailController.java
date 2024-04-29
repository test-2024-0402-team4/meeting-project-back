package com.meeteam4.meeting.controller;

import com.meeteam4.meeting.dto.EmailTeacherProfileReqDto;
import com.meeteam4.meeting.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/send")
    @ResponseBody
    public ResponseEntity<?> send(EmailTeacherProfileReqDto teacherProfileReqDto) throws Exception {
        emailService.sendTeacherProfile(teacherProfileReqDto);
        return ResponseEntity.ok(null);
    }
}
