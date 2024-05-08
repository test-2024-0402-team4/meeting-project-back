package com.meeteam4.meeting.service;

import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.entity.*;
import com.meeteam4.meeting.repository.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    // 계정 비활성화
    public void disableAccount(int userId) {
        adminMapper.disableAccount(userId);
    }

    // 신고 된 학생 게시글 조회
    public List<DeclareStudentBoardRespDto> getDeclareStudentBoard() {
        List<StudentBoardDeclare> studentBoardDeclares = adminMapper.getDeclareStudentBoard();
        List<DeclareStudentBoardRespDto> declareStudentBoardList = new ArrayList<>();

        for (StudentBoardDeclare studentBoardDeclare : studentBoardDeclares) {
            DeclareStudentBoardRespDto declareStudentBoardRespDto = studentBoardDeclare.toDeclareStudentBoardRespDto();
            declareStudentBoardRespDto.setTitle(studentBoardDeclare.getStudentBoard().getTitle());
            declareStudentBoardRespDto.setContent(studentBoardDeclare.getStudentBoard().getContent());

            declareStudentBoardList.add(declareStudentBoardRespDto);
        }
        return declareStudentBoardList;
    }
    // 신고 된 선생님 게시글 조회
    public List<DeclareTeacherBoardRespDto> getDeclareTeacherBoard() {
        List<TeacherBoardDeclare> teacherBoardDeclares = adminMapper.getDeclareTeacherBoard();
        List<DeclareTeacherBoardRespDto> declareTeacherBoardList = new ArrayList<>();

        for (TeacherBoardDeclare teacherBoardDeclare : teacherBoardDeclares) {
            DeclareTeacherBoardRespDto declareStudentBoardRespDto = teacherBoardDeclare.toDeclareTeacherBoardRespDto();
            declareStudentBoardRespDto.setTitle(teacherBoardDeclare.getTeacherBoard().getTitle());
            declareStudentBoardRespDto.setContent(teacherBoardDeclare.getTeacherBoard().getContent());

            declareTeacherBoardList.add(declareStudentBoardRespDto);
        }

        return declareTeacherBoardList;
    }
    // 신고 된 공부방 게시글 조회
    public List<DeclareStudyBoardRespDto> getDeclareStudyBoard() {
        List<StudyBoardDeclare> studyBoardDeclares = adminMapper.getDeclareStudyBoard();
        List<DeclareStudyBoardRespDto> declareStudyBoardList = new ArrayList<>();

        for (StudyBoardDeclare studyBoardDeclare : studyBoardDeclares) {
            DeclareStudyBoardRespDto declareStudyBoardRespDto = studyBoardDeclare.toDeclareStudyBoardRespDto();
            declareStudyBoardRespDto.setTitle(studyBoardDeclare.getStudyBoard().getTitle());
            declareStudyBoardRespDto.setContent(studyBoardDeclare.getStudyBoard().getContent());

            declareStudyBoardList.add(declareStudyBoardRespDto);
        }

        return declareStudyBoardList;
    }

    //학생 글에 신고 된 댓글 조회
    public List<DeclareStudentBoardCommentRespDto> getDeclareStudentComment() {
        List<StudentCommentDeclare> commentDeclares = adminMapper.getDeclareStudentComment();
        List<DeclareStudentBoardCommentRespDto> declareStudentCommentList = new ArrayList<>();

        for (StudentCommentDeclare commentDeclare : commentDeclares) {
            DeclareStudentBoardCommentRespDto declareStudyBoardRespDto = commentDeclare.toDeclareStudyBoardCommentRespDto();
            declareStudyBoardRespDto.setStudentBoardId(commentDeclare.getStudentComment().getStudentBoardId());
            declareStudyBoardRespDto.setNickname(commentDeclare.getStudentComment().getNickname());
            declareStudyBoardRespDto.setComment(commentDeclare.getStudentComment().getComment());
            declareStudyBoardRespDto.setUpdateDate(commentDeclare.getStudentComment().getUpdateDate());
            declareStudyBoardRespDto.setTitle(commentDeclare.getStudentBoard().getTitle());

            declareStudentCommentList.add(declareStudyBoardRespDto);
        }

        return declareStudentCommentList;
    }
    //선생님 글에 신고 된 댓글 조회
    public List<DeclareTeacherBoardCommentRespDto> getDeclareTeacherComment() {
        List<TeacherCommentDeclare> commentDeclares = adminMapper.getDeclareTeacherComment();
        List<DeclareTeacherBoardCommentRespDto> declareTeacherCommentList = new ArrayList<>();

        for (TeacherCommentDeclare commentDeclare : commentDeclares) {
            DeclareTeacherBoardCommentRespDto declareTeacherBoardRespDto = commentDeclare.toDeclareStudyBoardCommentRespDto();
            declareTeacherBoardRespDto.setTeacherBoardId(commentDeclare.getTeacherBoard().getTeacherBoardId());
            declareTeacherBoardRespDto.setNickname(commentDeclare.getTeacherComment().getNickname());
            declareTeacherBoardRespDto.setComment(commentDeclare.getTeacherComment().getComment());
            declareTeacherBoardRespDto.setUpdateDate(commentDeclare.getTeacherComment().getUpdateDate());
            declareTeacherBoardRespDto.setTitle(commentDeclare.getTeacherBoard().getTitle());

            declareTeacherCommentList.add(declareTeacherBoardRespDto);
        }

        return declareTeacherCommentList;
    }
    //공부방 글에 신고 된 댓글 조회
    public List<DeclareStudyBoardCommentRespDto> getDeclareStudyComment() {
        List<StudyCommentDeclare> commentDeclares = adminMapper.getDeclareStudyComment();
        List<DeclareStudyBoardCommentRespDto> declareStudyCommentList = new ArrayList<>();

        for (StudyCommentDeclare studyCommentDeclare : commentDeclares) {
            DeclareStudyBoardCommentRespDto declareStudyBoardRespDto = studyCommentDeclare.toDeclareStudyBoardCommentRespDto();
            declareStudyBoardRespDto.setStudyBoardId(studyCommentDeclare.getStudyBoard().getStudyBoardId());
            declareStudyBoardRespDto.setNickname(studyCommentDeclare.getStudyComment().getNickname());
            declareStudyBoardRespDto.setComment(studyCommentDeclare.getStudyComment().getComment());
            declareStudyBoardRespDto.setUpdateDate(studyCommentDeclare.getStudyComment().getUpdateDate());
            declareStudyBoardRespDto.setTitle(studyCommentDeclare.getStudyBoard().getTitle());

            declareStudyCommentList.add(declareStudyBoardRespDto);
        }

        return declareStudyCommentList;
    }

    // 계정 권한 확인 (선생, 학생 true = 학생, false = 선생)
    public boolean getUserStatus(int userId) {
        boolean result = true;
        Integer status = adminMapper.getUserStatus(userId);
        System.out.println(status);
        if(status == null) {
            result = false;
        }
        return result;
    }

}
