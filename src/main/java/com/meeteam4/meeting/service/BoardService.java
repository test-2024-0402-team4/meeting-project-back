package com.meeteam4.meeting.service;

import com.meeteam4.meeting.dto.BoardWriteReqDto;
import com.meeteam4.meeting.dto.StudentBoardListReqDto;
import com.meeteam4.meeting.dto.StudentBoardListRespDto;
import com.meeteam4.meeting.dto.StudentCommentPageRespDto;
import com.meeteam4.meeting.entity.StudentBoard;
import com.meeteam4.meeting.repository.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BoardService {
    @Autowired
    private BoardMapper boardMapper;
    public void saveBoard(BoardWriteReqDto boardWriteReqDto){
        boardMapper.saveBoard(boardWriteReqDto.toEntity());
    }


    public List<StudentBoardListRespDto> searchBoards(StudentBoardListReqDto studentBoardListReqDto){

        int startIndex = (studentBoardListReqDto.getPage()-1)* studentBoardListReqDto.getCount();

        List<StudentBoard> boards = boardMapper.searchBoard(
                startIndex,
                studentBoardListReqDto.getCount(),
                studentBoardListReqDto.getSearchText()
        );

        System.out.println(boards);


        return boards.stream().map(StudentBoard :: toStudentBoardListRespDto).collect(Collectors.toList());
    }
     public StudentCommentPageRespDto getSingleBoards(){

       StudentBoard board = boardMapper.getSingleBoard();

       return board.toStudentCommentPageRespDto();
    }

    public void deleteBoard(int studentBoardId){
        boardMapper.deleteBoardByBoardId(studentBoardId);
    }


}
