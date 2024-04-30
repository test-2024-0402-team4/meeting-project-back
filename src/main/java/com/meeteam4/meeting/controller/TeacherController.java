package com.meeteam4.meeting.controller;

import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/profile")
    public ResponseEntity<?> saveTeacherProfile(@RequestBody TeacherProfileReqDto teacherProfileReqDto) {

        accountService.saveTeacherProfile(teacherProfileReqDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/tutee/posters")
    public ResponseEntity<?> getPosters(SearchPosterReqDto searchPosterReqDto) {
        System.out.println(searchPosterReqDto);
        return ResponseEntity.ok(accountService.getStudentPosters(searchPosterReqDto));
    }

    @GetMapping("/tutee/poster")
    public ResponseEntity<?> getPoster(@RequestParam("posterId") Integer posterId) {
        System.out.println(posterId);
        return ResponseEntity.ok(accountService.getStudentPoster(posterId));
    }


    @PostMapping("/tutee/poster")
    public ResponseEntity<?> saveStudentPoster(@RequestBody PosterReqDto posterReqDto) {

        return ResponseEntity.ok(accountService.saveStudentPoster(posterReqDto));
    }
    @GetMapping("/tutee/profile")
    public ResponseEntity<?> getTuteeProfile(Integer userId) {
        System.out.println(accountService.getStudentProfile(userId));
        return ResponseEntity.ok(accountService.getStudentProfile(userId));
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
        return ResponseEntity.ok(accountService.getTeacherProfileRespDto(userId));
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getTeacherProfile(@PathVariable int userId){
        return  ResponseEntity.ok(accountService.getStudentProfile(userId));
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

        return ResponseEntity.ok(accountService.getTeacherProfileRespDto(userId));
    }

}
