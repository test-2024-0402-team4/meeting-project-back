package com.meeteam4.meeting.controller;

import com.meeteam4.meeting.dto.EmailApplyReqDto;
import com.meeteam4.meeting.dto.EmailTeacherProfileReqDto;
import com.meeteam4.meeting.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private EmailService emailService;
    @PostMapping("/send/auth")
    @ResponseBody
    public ResponseEntity<?> send() throws Exception {
        return ResponseEntity.ok(emailService.sendAuthMail());
    }

    @GetMapping("/authenticate")
    public String resultPage(Model model, @RequestParam String authToken){
        Map<String, Object> resultMap = emailService.authenticate(authToken);
        System.out.println(model.addAllAttributes(resultMap));
        model.addAllAttributes(resultMap);

        return "result_page";

    }

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
