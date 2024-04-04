package com.meeteam4.meeting.controller.board;

import com.meeteam4.meeting.dto.BoardWriteReqDto;
import com.meeteam4.meeting.dto.CommentReqDto;
import com.meeteam4.meeting.service.BoardService;
import com.meeteam4.meeting.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class MeetingController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody BoardWriteReqDto boardWriteReqDto){

        boardService.saveBoard(boardWriteReqDto);
        System.out.println(boardWriteReqDto);

        return ResponseEntity.ok(boardWriteReqDto);
    }

    @PostMapping("/comment")
    public ResponseEntity<?> comment(@RequestBody CommentReqDto commentReqDto){

        commentService.saveComment(commentReqDto);
        System.out.println(commentReqDto);

        return ResponseEntity.ok(commentReqDto);
    }
}
