package com.meeteam4.meeting.controller;

import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.service.AccountService;
import com.meeteam4.meeting.service.StudentService;
import com.meeteam4.meeting.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    // 선생님 필수정보 등록 요청
    @PostMapping("/profile")
    public ResponseEntity<?> saveTeacherProfile(@RequestBody TeacherProfileReqDto teacherProfileReqDto) {
        accountService.saveTeacherProfile(teacherProfileReqDto);
        return ResponseEntity.ok(null);
    }
    // 선생님 필수정보 수정 요청
    @PostMapping("/essentialInfo")
    public ResponseEntity<?> modifyTeacherEssentialInfo(@RequestBody TeacherProfileReqDto teacherProfileReqDto ) {
        accountService.modifyTeacherEssentialInfo(teacherProfileReqDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/tutee/posters")
    public ResponseEntity<?> getPosters(SearchPosterReqDto searchPosterReqDto) {
        return ResponseEntity.ok(studentService.getStudentPosters(searchPosterReqDto));
    }

    @GetMapping("/tutee/poster")
    public ResponseEntity<?> getPoster(@RequestParam("posterId") Integer posterId) {
        return ResponseEntity.ok(studentService.getStudentPoster(posterId));
    }


    @PostMapping("/tutee/poster")
    public ResponseEntity<?> saveStudentPoster(@RequestBody PosterReqDto posterReqDto) {

        return ResponseEntity.ok(accountService.saveStudentPoster(posterReqDto));
    }

    @GetMapping("/tutee/profile")
    public ResponseEntity<?> getTuteeProfile( Integer userId) {
        return ResponseEntity.ok(studentService.getStudentProfile(userId));
    }

    // 선생님 프로필 수정
    @PutMapping("/profile")
    public ResponseEntity<?> modifyProfile(@RequestBody TeacherProfileModifyDto teacherProfileModifyDto) {
        accountService.modifyTeacherProfile(teacherProfileModifyDto);
        return ResponseEntity.ok(true);
    }

    // 선생님 프로필 정보 가져오기
    @GetMapping("/tutor/profile")
    public ResponseEntity<?> getTeacherProfile(@RequestParam("userId") Integer userId) {
        return ResponseEntity.ok(teacherService.getTeacherProfile(userId));
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getStudentProfile(@PathVariable int userId){
        return  ResponseEntity.ok(studentService.getStudentProfile(userId));
    }

    @GetMapping("/mypage/boards/{userId}")
    public ResponseEntity<?> getTeacherMypageBoards(@PathVariable int userId, TeacherBoardListReqDto teacherBoardListReqDto){
        return ResponseEntity.ok(accountService.searchTeacherMypageBoards(teacherBoardListReqDto));
    }

    @GetMapping("/boards/count/{userId}")
    public ResponseEntity<?> teacherGetCount(@PathVariable int userId,TeacherBoardListReqDto teacherBoardListReqDto){

        return ResponseEntity.ok(accountService.getTeacherMypageCount(teacherBoardListReqDto));
    }

    @GetMapping("/mypage/boards/study/{userId}")
    public ResponseEntity<?> getTeacherStudyMypageBoards(@PathVariable int userId, StudyBoardListReqDto studyBoardListReqDto){
        return ResponseEntity.ok(accountService.searchTeacherStudyMypageBoards(studyBoardListReqDto));
    }

    @GetMapping("/boards/count/study/{userId}")
    public ResponseEntity<?> teacherStudyGetCount(@PathVariable int userId,StudyBoardListReqDto studyBoardListReqDto){

        return ResponseEntity.ok(accountService.getTeacherStudyMypageCount(studyBoardListReqDto));
    }

    @GetMapping("/mypage/profile/{userId}")
    public ResponseEntity<?> getTeacherMypageProfile(@PathVariable int userId){

        return ResponseEntity.ok(teacherService.getTeacherProfile(userId));
    }


}
