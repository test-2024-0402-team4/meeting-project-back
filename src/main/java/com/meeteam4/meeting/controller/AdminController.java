package com.meeteam4.meeting.controller;

import com.meeteam4.meeting.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/declare/student/board")
    private ResponseEntity<?> getDeclareStudentBoard() {
        return ResponseEntity.ok(adminService.getDeclareStudentBoard());
    }

    @GetMapping("/declare/teacher/board")
    private ResponseEntity<?> getDeclareTeacherBoard() {
        return ResponseEntity.ok(adminService.getDeclareTeacherBoard());
    }

    @GetMapping("/declare/study/board")
    private ResponseEntity<?> getDeclareStudyBoard() {
        return ResponseEntity.ok(adminService.getDeclareStudyBoard());
    }

    @GetMapping("/declare/study/comment")
    private ResponseEntity<?> getDeclareStudyComment() {
        return ResponseEntity.ok(adminService.getDeclareStudyComment());
    }

    @GetMapping("/declare/student/comment")
    private ResponseEntity<?> getDeclareStudentComment() {
        return ResponseEntity.ok(adminService.getDeclareStudentComment());
    }

    @GetMapping("/declare/teacher/comment")
    private ResponseEntity<?> getDeclareTeacherComment() {
        return ResponseEntity.ok(adminService.getDeclareTeacherComment());
    }
    @GetMapping("/api/users/{userId}")
    private ResponseEntity<?> getUserStatus(@PathVariable int userId) {

        return ResponseEntity.ok(adminService.getUserStatus(userId));
    }
    @GetMapping("/declare/user")
    public ResponseEntity<?> getDeclareUser() {
        return ResponseEntity.ok(adminService.getDeclareUser());
    }
}
