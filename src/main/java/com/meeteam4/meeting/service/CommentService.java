package com.meeteam4.meeting.service;

import com.meeteam4.meeting.dto.CommentReqDto;
import com.meeteam4.meeting.repository.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private BoardMapper boardMapper;
    public void saveComment(CommentReqDto commentReqDto){
        boardMapper.saveComment(commentReqDto.toEntity());
    }

}
