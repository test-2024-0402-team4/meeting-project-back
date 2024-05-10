package com.meeteam4.meeting.service;

import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.entity.StudentBoard;
import com.meeteam4.meeting.entity.StudentComment;
import com.meeteam4.meeting.entity.User;
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

     public StudentBoardPageRespDto getSingleBoards(int studentBoardId){

       StudentBoard board = boardMapper.getSingleBoard(studentBoardId);

       return board.toStudentBoardPageRespDto();
    }

    public StudentBoardPageRespDto getStudentId(int userId){

        StudentBoard board = boardMapper.getStudentId(userId);

        return board.toStudentId();
    }

    public StudentBoardPageRespDto getStudentIdByStudentBoardId(int studentBoardId){

        StudentBoard board = boardMapper.getStudentIdByStudentBoardId(studentBoardId);

        return board.toStudentId();
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteBoard(int studentBoardId){
        boardMapper.deleteBoardByBoardId(studentBoardId);
    }


    @Transactional(rollbackFor = Exception.class)
    public void updateBoard(UpdateBoardReqDto updateBoardReqDto){
        boardMapper.updateBoardByBoardId(updateBoardReqDto.toEntity());
    }

    public void updateViewCount(int studentBoardId){
        boardMapper.updateBoardViewByBoardId(studentBoardId);
    }

    public StudentBoardPageRespDto getBoardGenderImg(int studentId){

        StudentBoard board = boardMapper.getSingleBoard(studentId);

        return board.toStudentBoardPageRespDto();
    }

    public GenderRespDto getStudentGenderType(int studentId){

        Student board = boardMapper.getBoardGenderImg(studentId);

        return board.toEntity();
    }

    public NicknameRespDto getUserNickname(int userId){
        User board = boardMapper.getUserNickname(userId);
        return board.toNickname();
    }



}
