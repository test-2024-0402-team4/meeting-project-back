package com.meeteam4.meeting.controller;

import com.meeteam4.meeting.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @PutMapping("/users/{userId}/disable")
    public ResponseEntity<?> disableAccount(@PathVariable int userId) {
        adminService.disableAccount(userId);
        return ResponseEntity.ok("사용자 계정이 성공적으로 비활성화되었습니다.");
    }
}
