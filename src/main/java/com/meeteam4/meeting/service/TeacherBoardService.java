package com.meeteam4.meeting.service;

import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.entity.*;
import com.meeteam4.meeting.repository.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherBoardService {
    @Autowired
    private BoardMapper boardMapper;
    public void saveTeacherBoard(TeacherBoardWriteReqDto teacherBoardWriteReqDto){
        boardMapper.saveTeacherBoard(teacherBoardWriteReqDto.toEntity());
    }

    public List<TeacherBoardListRespDto> searchTeacherBoards(TeacherBoardListReqDto teacherBoardListReqDto){

        int startIndex = (teacherBoardListReqDto.getPage()-1)* teacherBoardListReqDto.getCount();

        List<TeacherBoard> teacherBoards = boardMapper.searchTeacherBoard(
                startIndex,
                teacherBoardListReqDto.getCount(),
                teacherBoardListReqDto.getSearchText()
        );

        return teacherBoards.stream().map(TeacherBoard :: toTeacherBoardListRespDto).collect(Collectors.toList());
    }

    public TeacherCountRespDto getTeacherCount(TeacherBoardListReqDto teacherBoardListReqDto){
        int teacherCount = boardMapper.getTeacherCount(
                teacherBoardListReqDto.getCount(),
                teacherBoardListReqDto.getSearchText()
        );

        int maxPageNumber = (int) Math.ceil(((double) teacherCount) / teacherBoardListReqDto.getCount());

        return TeacherCountRespDto.builder()
                .totalCount(teacherCount)
                .maxPageNumber(maxPageNumber)
                .build();
    }

    public TeacherBoardPageRespDto getSingleTeacherBoard(int teacherBoardId){

        TeacherBoard teacherBoard = boardMapper.getSingleTeacherBoard(teacherBoardId);

        return teacherBoard.toTeacherBoardPageRespDto();
    }

    public TeacherIdRespDto getTeacherId(int userId){

        TeacherBoard board = boardMapper.getTeacherId(userId);

        return board.toTeacherIdRespDto();
    }

    public TeacherIdRespDto getTeacherIdByTeacherBoardId(int teacherBoardId){

        TeacherBoard board = boardMapper.getTeacherIdByTeacherBoardId(teacherBoardId);

        return board.toTeacherIdRespDto();
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteTeacherBoard(int teacherBoardId){
        boardMapper.deleteTeacherBoardByBoardId(teacherBoardId);
    }


    @Transactional(rollbackFor = Exception.class)
    public void updateTeacherBoard(UpdateTeacherBoardReqDto UpdateTeacherBoardReqDto){
        boardMapper.updateTeacherBoardByBoardId(UpdateTeacherBoardReqDto.toEntity());
    }

    public void updateViewCount(int teacherBoardId){
        boardMapper.updateBoardViewByTeacherBoardId(teacherBoardId);
    }

    public GenderRespDto getTeacherGenderType(int teacherId){

        Teacher board = boardMapper.getTeacherBoardGenderImg(teacherId);

        return board.toEntity();
    }

    public NicknameRespDto getTeahcerNickname(int userId){
        User board = boardMapper.getTeacherNickname(userId);
        return board.toNickname();
    }

}
