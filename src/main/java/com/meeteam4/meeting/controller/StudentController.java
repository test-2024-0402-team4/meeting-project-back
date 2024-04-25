package com.meeteam4.meeting.controller;


import com.meeteam4.meeting.dto.PosterReqDto;
import com.meeteam4.meeting.dto.SearchProfilesReqDto;
import com.meeteam4.meeting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/poster")
    public ResponseEntity<?> saveStudentPoster(@RequestBody PosterReqDto posterReqDto) {
        return ResponseEntity.ok(accountService.saveStudentPoster(posterReqDto));
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
}
