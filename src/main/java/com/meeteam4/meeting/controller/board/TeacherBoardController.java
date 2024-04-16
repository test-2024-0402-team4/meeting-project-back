package com.meeteam4.meeting.controller.board;

import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.service.CommentService;
import com.meeteam4.meeting.service.TeacherBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherBoardController {
    @Autowired
    private TeacherBoardService teacherBoardService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/board")        //선생게시판 작성하기
    public ResponseEntity<?> teacherBoardWrite(@RequestBody TeacherBoardWriteReqDto teacherBoardWriteReqDto){

        teacherBoardService.saveTeacherBoard(teacherBoardWriteReqDto);

        return ResponseEntity.ok(teacherBoardWriteReqDto);
    }

    @GetMapping("/boards")    //선생 게시판 목록보기
    public ResponseEntity<?> teacherBoardList(TeacherBoardListReqDto teacherBoardListReqDto){
        return ResponseEntity.ok(teacherBoardService.searchTeacherBoards(teacherBoardListReqDto));
    }

    @GetMapping("/boards/count") //선생 게시판 목록에서 페이지만들때
    public ResponseEntity<?> teacherGetCount(TeacherBoardListReqDto teacherBoardListReqDto){

        return ResponseEntity.ok(teacherBoardService.getTeacherCount(teacherBoardListReqDto));
    }

    @GetMapping("/board/{teacherBoardId}")  //선생게시판 단건 조회
    public ResponseEntity<?> teacherBoardListSingle(@PathVariable int teacherBoardId){

        return ResponseEntity.ok(teacherBoardService.getSingleTeacherBoard(teacherBoardId));
    }

    @DeleteMapping("/board/{teacherBoardId}")  // 선생게시판 단건 삭제
    public ResponseEntity<?> deleteSingleTeacherBoard(@PathVariable int teacherBoardId){

        teacherBoardService.deleteTeacherBoard(teacherBoardId);

        return ResponseEntity.ok(true);
    }
    @PutMapping("/board/{teacherBoardId}") // 선생게시판 단건 수정
    public ResponseEntity<?> updateTeacherBoard(@PathVariable int teacherBoardId, @RequestBody UpdateTeacherBoardReqDto updateTeacherBoardReqDto){
        teacherBoardService.updateTeacherBoard(updateTeacherBoardReqDto);

        return ResponseEntity.ok(true);
    }
    @PostMapping("/board/comment/{teacherBoardId}")  //선생게시판 댓글 등록
    public ResponseEntity<?> studentBoardComment(@PathVariable int teacherBoardId, @RequestBody TeacherCommentReqDto teacherCommentReqDto){
        commentService.saveTeacherComment(teacherCommentReqDto);
        return ResponseEntity.ok(teacherCommentReqDto);
    }

    @GetMapping("/board/comments/{teacherBoardId}")
    public ResponseEntity<?> getTeacherComments(@PathVariable int teacherBoardId){
        return ResponseEntity.ok(commentService.getTeacherComment(teacherBoardId));
    }
    @DeleteMapping("/board/comment/{teacherCommentId}")
    public ResponseEntity<?> deleteTeacherComment(@PathVariable int teacherCommentId){
        commentService.deleteTeacherComment(teacherCommentId);
        return ResponseEntity.ok(true);
    }
    @PutMapping("/board/comment/{teacherCommentId}")
    public ResponseEntity<?> updateTeacherComment(@PathVariable int teacherCommentId , @RequestBody UpdateTeacherCommentReqDto updateTeacherCommentReqDto){
        commentService.updateTeacherComment(updateTeacherCommentReqDto);
        return ResponseEntity.ok(true);
    }

}
