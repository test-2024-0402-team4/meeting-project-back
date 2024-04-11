package com.meeteam4.meeting.controller.board;

import com.meeteam4.meeting.dto.*;
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

    @GetMapping("/student/boardList/count")
    public ResponseEntity<?> studentGetCount(StudentBoardListReqDto studentBoardListReqDto){

        return ResponseEntity.ok(boardService.getStudentCount(studentBoardListReqDto));
    }

    @GetMapping("/student/comment/{studentBoardId}")
    public ResponseEntity<?> studentBoardListSingle(@PathVariable int studentBoardId){

        return ResponseEntity.ok(boardService.getSingleBoards(studentBoardId));
    }

    @DeleteMapping("/student/comment/{studentBoardId}")
    public ResponseEntity<?> deleteSingleBoard(@PathVariable int studentBoardId){

        boardService.deleteBoard(studentBoardId);

        return ResponseEntity.ok(true);
    }

    @PutMapping("/student/update/{studentBoardId}")
    public ResponseEntity<?> updateBoard(@PathVariable int studentBoardId, @RequestBody UpdateBoardReqDto updateBoardReqDto){
        boardService.updateBoard(updateBoardReqDto);
        System.out.println(updateBoardReqDto.toEntity());
        return ResponseEntity.ok(true);
    }
}
