package com.meeteam4.meeting.controller.board;

import com.meeteam4.meeting.dto.NoticeBoardListReqDto;
import com.meeteam4.meeting.dto.StudentBoardListReqDto;
import com.meeteam4.meeting.service.BoardService;
import com.meeteam4.meeting.service.NoticeBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class NoticeBoardController {
    @Autowired
    private NoticeBoardService noticeBoardService;

    @GetMapping("/boards")
    public ResponseEntity<?> noticeBoardList(NoticeBoardListReqDto noticeBoardListReqDto){
        return ResponseEntity.ok(noticeBoardService.searchNoticeBoards(noticeBoardListReqDto));
    }

    @GetMapping("/boards/count")
    public ResponseEntity<?> noticeGetCount(NoticeBoardListReqDto noticeBoardListReqDto){

        return ResponseEntity.ok(noticeBoardService.getNoticeCount(noticeBoardListReqDto));
    }

    @GetMapping("/board/{noticeId}")
    public ResponseEntity<?> noticeBoardListSingle(@PathVariable int noticeId){

        return ResponseEntity.ok(noticeBoardService.getSingleNoticeBoards(noticeId));
    }
}
