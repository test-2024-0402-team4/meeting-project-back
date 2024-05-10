package com.meeteam4.meeting.controller.board;

import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.service.BoardService;
import com.meeteam4.meeting.service.NoticeBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/board/view/{noticeId}")
    public ResponseEntity<?> updateViewCount(@PathVariable int noticeId){

        noticeBoardService.updateViewCount(noticeId);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/declare/{studentBoardId}")
    public ResponseEntity<?> declareInsert(@PathVariable int studentBoardId ,@RequestBody DeclareReqDto declareReqDto){

        noticeBoardService.saveDeclare(declareReqDto);

        return ResponseEntity.ok(declareReqDto);
    }

    @PostMapping("/declare/student/comment/{studentCommentId}")
    public ResponseEntity<?> declareStudentCommentInsert(@PathVariable int studentCommentId ,@RequestBody DeclareReqDto declareReqDto){

        noticeBoardService.saveStudentCommentDeclare(declareReqDto);

        return ResponseEntity.ok(declareReqDto);
    }

    @PostMapping("/declare/teacher/{teacherBoardId}")
    public ResponseEntity<?> declareTeacherInsert(@PathVariable int teacherBoardId ,@RequestBody TeacherDeclareReqDto teacherDeclareReqDto){

        noticeBoardService.saveTeacherDeclare(teacherDeclareReqDto);

        return ResponseEntity.ok(teacherDeclareReqDto);
    }

    @PostMapping("/declare/teacher/comment/{teacherCommentId}")
    public ResponseEntity<?> declareTeacherCommentInsert(@PathVariable int teacherCommentId ,@RequestBody TeacherDeclareReqDto teacherDeclareReqDto){

        noticeBoardService.saveTeacherCommentDeclare(teacherDeclareReqDto);

        return ResponseEntity.ok(teacherDeclareReqDto);
    }

    @PostMapping("/declare/study/{studyBoardId}")
    public ResponseEntity<?> declareStudyInsert(@PathVariable int studyBoardId ,@RequestBody StudyDeclareReqDto studyDeclareReqDto){

        noticeBoardService.saveStudyDeclare(studyDeclareReqDto);

        return ResponseEntity.ok(studyDeclareReqDto);
    }

    @PostMapping("/declare/study/comment/{studyCommentId}")
    public ResponseEntity<?> declareStudyCommentInsert(@PathVariable int studyCommentId ,@RequestBody StudyDeclareReqDto studyDeclareReqDto){

        noticeBoardService.saveStudyCommentDeclare(studyDeclareReqDto);

        return ResponseEntity.ok(studyDeclareReqDto);
    }
}














