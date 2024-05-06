package com.meeteam4.meeting.controller;


import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.entity.Poster;
import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.security.PrincipalStudent;
import com.meeteam4.meeting.security.PrincipalUser;
import com.meeteam4.meeting.service.AccountService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/principal")
    public ResponseEntity<?> getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        return ResponseEntity.ok(principalUser);
    }

    @PutMapping("/profile/image/{userId}")
    public ResponseEntity<?> updateImgUrl(@PathVariable int userId, @RequestBody UpdateImgUrlReqDto updateImgUrlReqDto){
        accountService.updateImgUrl(updateImgUrlReqDto);
        return ResponseEntity.ok(true);

    }

}



