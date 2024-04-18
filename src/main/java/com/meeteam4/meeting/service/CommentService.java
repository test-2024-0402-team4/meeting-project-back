package com.meeteam4.meeting.service;

import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.entity.StudentComment;
import com.meeteam4.meeting.entity.StudyComment;
import com.meeteam4.meeting.entity.TeacherComment;
import com.meeteam4.meeting.repository.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private BoardMapper boardMapper;
    public void saveComment(CommentReqDto commentReqDto){
        boardMapper.saveComment(commentReqDto.toEntity());
    }

    @Transactional(rollbackFor = Exception.class)
    public List<StudentCommentRespDto> getStudentComment(int studentBoardId){

        List<StudentComment> comments = boardMapper.getStudentComment(studentBoardId);

        return comments.stream().map(StudentComment :: toStudentCommentRespDto).collect(Collectors.toList());

    }
    public void deleteStudentComment(int studentCommentId){
        boardMapper.deleteStudentCommentByCommentId(studentCommentId);
    }

    public void updateComment(UpdateCommentReqDto updateCommentReqDto){
        boardMapper.updateCommentByCommentId(updateCommentReqDto.toEntity());
    }

    public void saveTeacherComment(TeacherCommentReqDto teacherCommentReqDto){
        boardMapper.saveTeacherComment(teacherCommentReqDto.toEntity());
    }
    @Transactional(rollbackFor = Exception.class)
    public List<TeacherCommentRespDto> getTeacherComment(int teacherBoardId){

        List<TeacherComment> comments = boardMapper.getTeacherComment(teacherBoardId);

        return comments.stream().map(TeacherComment :: toTeacherCommentRespDto).collect(Collectors.toList());
    }
    public void deleteTeacherComment(int teacherCommentId){
        boardMapper.deleteTeacherCommentByCommentId(teacherCommentId);
    }

    public void updateTeacherComment(UpdateTeacherCommentReqDto updateTeacherCommentReqDto){
        boardMapper.updateTeacherCommentByCommentId(updateTeacherCommentReqDto.toEntity());
    }




    public void saveStudyComment(StudyCommentReqDto studyCommentReqDto){
        boardMapper.saveStudyComment(studyCommentReqDto.toEntity());
    }
    @Transactional(rollbackFor = Exception.class)
    public List<StudyCommentRespDto> getStudyComment(int studyBoardId){

        List<StudyComment> studyComments = boardMapper.getStudyComment(studyBoardId);

        return studyComments.stream().map(StudyComment :: toStudyCommentRespDto).collect(Collectors.toList());
    }
    public void deleteStudyComment(int studyCommentId){
        boardMapper.deleteStudyCommentByCommentId(studyCommentId);
    }

    public void updateStudyComment(UpdateStudyCommentReqDto updateStudyCommentReqDto){
        boardMapper.updateStudyCommentByCommentId(updateStudyCommentReqDto.toEntity());
    }
}
