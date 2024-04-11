package com.meeteam4.meeting.service;

import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.entity.StudentBoard;
import com.meeteam4.meeting.repository.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public StudentCountRespDto getStudentCount(StudentBoardListReqDto studentBoardListReqDto){
        int studentCount = boardMapper.getStudentCount(
                studentBoardListReqDto.getCount(),
                studentBoardListReqDto.getSearchText()
        );

        int maxPageNumber = (int) Math.ceil(((double) studentCount) / studentBoardListReqDto.getCount());

        return StudentCountRespDto.builder()
                .totalCount(studentCount)
                .maxPageNumber(maxPageNumber)
                .build();
    }


     public StudentCommentPageRespDto getSingleBoards(int studentBoardId){

       StudentBoard board = boardMapper.getSingleBoard(studentBoardId);

       return board.toStudentCommentPageRespDto();
    }
    @Transactional(rollbackFor = Exception.class)
    public void deleteBoard(int studentBoardId){
        boardMapper.deleteBoardByBoardId(studentBoardId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateBoard(UpdateBoardReqDto updateBoardReqDto){
        boardMapper.updateBoardByBoardId(updateBoardReqDto.toEntity());
    }
}
