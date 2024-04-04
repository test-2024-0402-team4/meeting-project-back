package com.meeteam4.meeting.service;

import com.meeteam4.meeting.dto.BoardWriteReqDto;
import com.meeteam4.meeting.repository.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BoardService {
    @Autowired
    private BoardMapper boardMapper;
    public void saveBoard(BoardWriteReqDto boardWriteReqDto){
        boardMapper.saveBoard(boardWriteReqDto.toEntity());
    }
}
