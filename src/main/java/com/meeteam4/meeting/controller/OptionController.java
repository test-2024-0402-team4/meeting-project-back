package com.meeteam4.meeting.controller;

import com.meeteam4.meeting.service.AuthOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/option")
public class OptionController {

    @Autowired
    private AuthOptionService authOptionService;

    // Front - <select /> 사용해서 DB에 있는 option 값 불러오기 위함
    @GetMapping("/regions")
    public ResponseEntity<?> getRegion() {
        return ResponseEntity.ok(authOptionService.getRegion());
    }
    @GetMapping("/studentTypes")
    public ResponseEntity<?> getStudentType() {
        return ResponseEntity.ok(authOptionService.getStudentType());
    }
    @GetMapping("/universities")
    public ResponseEntity<?> getUniversity() {
        return ResponseEntity.ok(authOptionService.getUniversity());
    }
    @GetMapping("/graduateStates")
    public ResponseEntity<?> getGraduateState() {
        return ResponseEntity.ok(authOptionService.getGraduateState());
    }

}
