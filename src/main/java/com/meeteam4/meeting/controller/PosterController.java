package com.meeteam4.meeting.controller;


import com.meeteam4.meeting.dto.SearchPosterReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/poster")
public class PosterController {

    @GetMapping("/posterList")
    public ResponseEntity<?> getPostList(@RequestBody SearchPosterReqDto searchPosterDto) {


        return ResponseEntity.ok(null);
    }


}
