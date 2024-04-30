package com.meeteam4.meeting.controller;

import com.meeteam4.meeting.dto.EmailApplyReqDto;
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

    @PostMapping("/send/profile")
    @ResponseBody
    public ResponseEntity<?> sendProfile(@RequestBody EmailTeacherProfileReqDto emailTeacherProfileReqDto) throws Exception {
        System.out.println(emailTeacherProfileReqDto);
        emailService.sendTeacherProfile(emailTeacherProfileReqDto);
        return ResponseEntity.ok(null);
    }
    @PostMapping("/send/lesson")
    @ResponseBody
    public ResponseEntity<?> sendLessonApply(@RequestBody EmailApplyReqDto emailApplyReqDto) throws Exception {
        System.out.println(emailApplyReqDto);
        emailService.sendApplyEmail(emailApplyReqDto);
        return ResponseEntity.ok(null);
    }
}
