package com.meeteam4.meeting.controller;


import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/poster")
    public ResponseEntity<?> modifyPoster(@RequestBody StudentPosterModify studentPosterModify) {
        return ResponseEntity.ok(accountService.modifyStudentPoster(studentPosterModify));
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
        return  ResponseEntity.ok(accountService.getStudentProfile(userId));
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

}
