package com.meeteam4.meeting.controller;

import com.meeteam4.meeting.dto.PosterReqDto;
import com.meeteam4.meeting.dto.SearchPosterReqDto;
import com.meeteam4.meeting.dto.TeacherProfileReqDto;
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



}
