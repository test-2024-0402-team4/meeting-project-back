package com.meeteam4.meeting.service;

import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.entity.StudentBoard;
import com.meeteam4.meeting.entity.StudyBoard;
import com.meeteam4.meeting.repository.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyBoardService {

    @Autowired
    private BoardMapper boardMapper;
    public void saveStudyBoard(StudyBoardWriteReqDto studyBoardWriteReqDto){
        boardMapper.saveStudyBoard(studyBoardWriteReqDto.toEntity());
    }

    public List<StudyBoardListRespDto> searchStudyBoards(StudyBoardListReqDto studyBoardListReqDto) {
        int startIndex = (studyBoardListReqDto.getPage() - 1) * studyBoardListReqDto.getCount();

        List<StudyBoard> studyBoards = boardMapper.searchStudyBoard(
                startIndex,
                studyBoardListReqDto.getCount(),
                studyBoardListReqDto.getSearchText()
        );

        return studyBoards.stream().map(StudyBoard::toStudyBoardListRespDto).collect(Collectors.toList());
        }

        public StudyCountRespDto getStudyCount(StudyBoardListReqDto studyBoardListReqDto){
            int studyCount = boardMapper.getStudyCount(
                    studyBoardListReqDto.getCount(),
                    studyBoardListReqDto.getSearchText()
            );

            int maxPageNumber = (int) Math.ceil(((double) studyCount) / studyBoardListReqDto.getCount());

            return StudyCountRespDto.builder()
                    .totalCount(studyCount)
                    .maxPageNumber(maxPageNumber)
                    .build();
        }

        public StudyBoardPageRespDto getSingleStudyBoards(int studyBoardId){

            StudyBoard studyBoard = boardMapper.getSingleStudyBoard(studyBoardId);

            return studyBoard.toStudyBoardPageRespDto();
        }

    @Transactional(rollbackFor = Exception.class)
    public void deleteStudyBoard(int studyBoardId){
        boardMapper.deleteStudyBoardByBoardId(studyBoardId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateStudyBoard(UpdateStudyBoardReqDto updateStudyBoardReqDto){
        boardMapper.updateStudyBoardByBoardId(updateStudyBoardReqDto.toEntity());
    }




}
