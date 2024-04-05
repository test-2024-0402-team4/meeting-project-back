package com.meeteam4.meeting.controller.board;

import com.meeteam4.meeting.dto.BoardWriteReqDto;
import com.meeteam4.meeting.dto.CommentReqDto;
import com.meeteam4.meeting.dto.StudentBoardListReqDto;
import com.meeteam4.meeting.service.BoardService;
import com.meeteam4.meeting.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class MeetingController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/student")
    public ResponseEntity<?> studentWrite(@RequestBody BoardWriteReqDto boardWriteReqDto){

        boardService.saveBoard(boardWriteReqDto);
        System.out.println(boardWriteReqDto);

        return ResponseEntity.ok(boardWriteReqDto);
    }

    @PostMapping("/student/comment")
    public ResponseEntity<?> studentComment(@RequestBody CommentReqDto commentReqDto){

        commentService.saveComment(commentReqDto);
        System.out.println(commentReqDto);

        return ResponseEntity.ok(commentReqDto);
    }

    @GetMapping("/student/boardList")
    public ResponseEntity<?> studentBoardList(StudentBoardListReqDto studentBoardListReqDto){
        return ResponseEntity.ok(boardService.searchBoards(studentBoardListReqDto));
    }
}
