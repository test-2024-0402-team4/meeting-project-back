package com.meeteam4.meeting.controller;


import com.meeteam4.meeting.dto.PosterReqDto;
import com.meeteam4.meeting.dto.SearchProfilesReqDto;
import com.meeteam4.meeting.dto.StudentProfileModifyDto;
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
//        System.out.println(studentProfileModifyDto);
        accountService.modifyStudentProfile(studentProfileModifyDto);
        return ResponseEntity.ok(true);
    }
}
