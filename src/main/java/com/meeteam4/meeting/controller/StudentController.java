package com.meeteam4.meeting.controller;



import com.meeteam4.meeting.dto.*;

import com.meeteam4.meeting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private AccountService accountService;

    // 공고 등록
    @PostMapping("/poster")
    public ResponseEntity<?> saveStudentPoster(@RequestBody PosterReqDto posterReqDto) {
        return ResponseEntity.ok(accountService.saveStudentPoster(posterReqDto));
    }
    // 올린 공고 수정
    @PostMapping("/poster/modify")
    public ResponseEntity<?> modifyPoster(@RequestBody PosterReqDto posterReqDto) {
        return ResponseEntity.ok(accountService.modifyStudentPoster(posterReqDto));
    }

    // 올린 공고 삭제
    @DeleteMapping("/poster/{posterId}")
    public ResponseEntity<?> deletePoster(@PathVariable int posterId) {
        return ResponseEntity.ok(accountService.deleteStudentPoster(posterId));
    }

    @GetMapping("/tutor/profiles")
    public ResponseEntity<?> searchTeacherProfiles(SearchProfilesReqDto searchProfilesReqDto) {
        System.out.println(searchProfilesReqDto);
        return ResponseEntity.ok(accountService.searchTeacherProfiles(searchProfilesReqDto));
    }

    @GetMapping("/tutor/profile")
    public ResponseEntity<?> getTeacherProfile(@RequestParam("userId") Integer userId) {
        return ResponseEntity.ok(accountService.getTeacherProfileRespDto(userId));
    }

    @GetMapping("/myposters")
    public ResponseEntity<?> getMyPosters(@RequestParam("userId") Integer userId) {
        System.out.println(accountService.getStudentMyPosters(userId));

        return ResponseEntity.ok(accountService.getStudentMyPosters(userId));
    }

    @GetMapping("/myposter")
    public ResponseEntity<?> getMyPoster(@RequestParam("posterId") Integer posterId) {
        System.out.println(accountService.getMyposter(posterId));
        return ResponseEntity.ok(accountService.getMyposter(posterId));
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getStudentProfile(@PathVariable int userId){
        System.out.println(accountService.getStudentProfile(userId));
        return ResponseEntity.ok(accountService.getStudentProfile(userId));
    }

    // 학생 프로필 수정
    @PutMapping("/profile")
    public ResponseEntity<?> modifyProfile(@RequestBody StudentProfileModifyDto studentProfileModifyDto) {
        accountService.modifyStudentProfile(studentProfileModifyDto);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/mypage/boards/{userId}")
    public ResponseEntity<?> getStudentMypageBoards(@PathVariable int userId,StudentBoardListReqDto studentBoardListReqDto){
        return ResponseEntity.ok(accountService.searchStudentMypageBoards(studentBoardListReqDto));
    }

    @GetMapping("/boards/count/{userId}")
    public ResponseEntity<?> studentGetCount(@PathVariable int userId,StudentBoardListReqDto studentBoardListReqDto){

        return ResponseEntity.ok(accountService.getStudentMypageCount(studentBoardListReqDto));
    }

    @GetMapping("/mypage/boards/study/{userId}")
    public ResponseEntity<?> getStudyMypageBoards(@PathVariable int userId, StudyBoardListReqDto studyBoardListReqDto){
        return ResponseEntity.ok(accountService.searchStudyMypageBoards(studyBoardListReqDto));
    }

    @GetMapping("/boards/count/study/{userId}")
    public ResponseEntity<?> studyGetCount(@PathVariable int userId,StudyBoardListReqDto studyBoardListReqDto){

        return ResponseEntity.ok(accountService.getStudyMypageCount(studyBoardListReqDto));
    }

    @PostMapping("/apply/detail")
    public ResponseEntity<?> saveApplicationDetail (@RequestBody Map<String, Integer> requestData) {
        int studentUserId = requestData.get("studentUserId");
        int teacherUserId = requestData.get("teacherUserId");

        accountService.saveApplicationDetails(studentUserId, teacherUserId);
        return ResponseEntity.ok(null);
    }
    @GetMapping("/apply/detail")
    public ResponseEntity<?> getUserIdByApplicationDetail (@RequestParam("studentUserId") int studentUserId) {
        return ResponseEntity.ok(accountService.getApplicationDetails(studentUserId));
    }

}
