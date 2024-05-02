package com.meeteam4.meeting.controller.board;

import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.repository.BoardMapper;
import com.meeteam4.meeting.service.CommentService;
import com.meeteam4.meeting.service.StudyBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/study")
public class StudyBoardController {
    @Autowired
    private StudyBoardService studyBoardService;

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private CommentService commentService;

    @PostMapping("/board")
    public ResponseEntity<?> studyBoardWrite(@RequestBody StudyBoardWriteReqDto studyBoardWriteReqDto) {
        studyBoardService.saveStudyBoard(studyBoardWriteReqDto);
        return ResponseEntity.ok(studyBoardWriteReqDto);
    }

    @GetMapping("/boards")
    public ResponseEntity<?> studyBoardList(StudyBoardListReqDto studyBoardListReqDto){
        return ResponseEntity.ok(studyBoardService.searchStudyBoards(studyBoardListReqDto));
    }

    @GetMapping("/boards/count")
    public ResponseEntity<?> studyGetCount(StudyBoardListReqDto studyBoardListReqDto){

        return ResponseEntity.ok(studyBoardService.getStudyCount(studyBoardListReqDto));
    }

    @GetMapping("/board/{studyBoardId}")
    public ResponseEntity<?> studyBoardListSingle(@PathVariable int studyBoardId){

        return ResponseEntity.ok(studyBoardService.getSingleStudyBoards(studyBoardId));
    }

    @GetMapping("/board/single/{studyBoardId}")
    public ResponseEntity<?> getUserIdByStudyBoardId(@PathVariable int studyBoardId){
        return ResponseEntity.ok(studyBoardService.getUserIdByStudyBoardId(studyBoardId));
    }

    @DeleteMapping("/board/{studyBoardId}")
    public ResponseEntity<?> deleteSingleStudyBoard(@PathVariable int studyBoardId){

        studyBoardService.deleteStudyBoard(studyBoardId);

        return ResponseEntity.ok(true);
    }
    @PutMapping("/board/{studyBoardId}")
    public ResponseEntity<?> updateStudyBoard(@PathVariable int studyBoardId, @RequestBody UpdateStudyBoardReqDto updateStudyBoardReqDto){
        studyBoardService.updateStudyBoard(updateStudyBoardReqDto);

        return ResponseEntity.ok(true);
    }

    @PutMapping("/board/view/{studyBoardId}")
    public ResponseEntity<?> updateViewCount(@PathVariable int studyBoardId){

        studyBoardService.updateViewCount(studyBoardId);
        return ResponseEntity.ok(true);
    }


    @PostMapping("/board/comment/{studyBoardId}")
    public ResponseEntity<?> studentBoardComment(@PathVariable int studyBoardId, @RequestBody StudyCommentReqDto studyCommentReqDto){

        commentService.saveStudyComment(studyCommentReqDto);


        return ResponseEntity.ok(studyCommentReqDto);
    }

    @GetMapping("/board/comments/{studyBoardId}")
    public ResponseEntity<?> getStudyComments(@PathVariable int studyBoardId){

        return ResponseEntity.ok(commentService.getStudyComment(studyBoardId));
    }
    @DeleteMapping("/board/comment/{studyCommentId}")
    public ResponseEntity<?> deleteStudyComment(@PathVariable int studyCommentId){
        commentService.deleteStudyComment(studyCommentId);
        return ResponseEntity.ok(true);
    }
    @PutMapping("/board/comment/{studyCommentId}")
    public ResponseEntity<?> updateComment(@PathVariable int studyCommentId , @RequestBody UpdateStudyCommentReqDto updateStudyCommentReqDto){
        commentService.updateStudyComment(updateStudyCommentReqDto);
        return ResponseEntity.ok(true);
    }


}
