package com.meeteam4.meeting.service;

import com.meeteam4.meeting.dto.CommentReqDto;
import com.meeteam4.meeting.dto.StudentCommentRespDto;
import com.meeteam4.meeting.dto.UpdateCommentReqDto;
import com.meeteam4.meeting.entity.StudentComment;
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
}
