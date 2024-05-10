package com.meeteam4.meeting.controller.board;

import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.service.BoardService;
import com.meeteam4.meeting.service.CommentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentBoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/board")
    public ResponseEntity<?> studentBoardWrite(@RequestBody BoardWriteReqDto boardWriteReqDto){

        boardService.saveBoard(boardWriteReqDto);
        System.out.println(boardWriteReqDto);

        return ResponseEntity.ok(boardWriteReqDto);
    }
    @GetMapping("/boards")
    public ResponseEntity<?> studentBoardList(StudentBoardListReqDto studentBoardListReqDto){
        return ResponseEntity.ok(boardService.searchBoards(studentBoardListReqDto));
    }

    @GetMapping("/boards/count")
    public ResponseEntity<?> studentGetCount(StudentBoardListReqDto studentBoardListReqDto){

        return ResponseEntity.ok(boardService.getStudentCount(studentBoardListReqDto));
    }

    @GetMapping("/board/{studentBoardId}")
    public ResponseEntity<?> studentBoardListSingle(@PathVariable int studentBoardId){

        return ResponseEntity.ok(boardService.getSingleBoards(studentBoardId));
    }

    @GetMapping("/board/write/{userId}")
    public ResponseEntity<?> getStudentIdByUserId(@PathVariable int userId){

        return ResponseEntity.ok(boardService.getStudentId(userId));
    }

    @GetMapping("/board/single/{studentBoardId}")
    public ResponseEntity<?> getStudentIdByStudentBoardId(@PathVariable int studentBoardId){
        return ResponseEntity.ok(boardService.getStudentIdByStudentBoardId(studentBoardId));
    }


    @DeleteMapping("/board/{studentBoardId}")
    public ResponseEntity<?> deleteSingleBoard(@PathVariable int studentBoardId){

        boardService.deleteBoard(studentBoardId);

        return ResponseEntity.ok(true);
    }

    @PutMapping("/board/{studentBoardId}")
    public ResponseEntity<?> updateBoard(@PathVariable int studentBoardId, @RequestBody UpdateBoardReqDto updateBoardReqDto){
        boardService.updateBoard(updateBoardReqDto);

        return ResponseEntity.ok(true);
    }

    @PutMapping("/board/view/{studentBoardId}")
    public ResponseEntity<?> updateViewCount(@PathVariable int studentBoardId){

        boardService.updateViewCount(studentBoardId);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/board/comment/{studentBoardId}")
    public ResponseEntity<?> studentBoardComment(@PathVariable int studentBoardId, @RequestBody CommentReqDto commentReqDto){

        commentService.saveComment(commentReqDto);


        return ResponseEntity.ok(commentReqDto);
    }

    @GetMapping("/board/comments/{studentBoardId}")
    public ResponseEntity<?> getStudentComments(@PathVariable int studentBoardId){

        return ResponseEntity.ok(commentService.getStudentComment(studentBoardId));
    }
    @DeleteMapping("/board/comment/{studentCommentId}")
    public ResponseEntity<?> deleteStudentComment(@PathVariable int studentCommentId){
        commentService.deleteStudentComment(studentCommentId);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/board/comment/{studentCommentId}")
    public ResponseEntity<?> updateComment(@PathVariable int studentCommentId , @RequestBody UpdateCommentReqDto updateCommentReqDto){
        commentService.updateComment(updateCommentReqDto);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/board/genderImg/{studentId}")
    public ResponseEntity<?> getGenderImg(@PathVariable int studentId){

        return ResponseEntity.ok(boardService.getStudentGenderType(studentId));
    }

    @GetMapping("/board/nickname/{userId}")
    public ResponseEntity<?> getUserNickname(@PathVariable int userId){
        return ResponseEntity.ok(boardService.getUserNickname(userId));
    }


}
