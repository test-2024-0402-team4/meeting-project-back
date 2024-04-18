package com.meeteam4.meeting.service;

import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.entity.Notice;
import com.meeteam4.meeting.entity.StudentBoard;
import com.meeteam4.meeting.repository.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeBoardService {
    @Autowired
    private BoardMapper boardMapper;

    public List<NoticeBoardListRespDto> searchNoticeBoards(NoticeBoardListReqDto noticeBoardListReqDto){

        int startIndex = (noticeBoardListReqDto.getPage()-1)* noticeBoardListReqDto.getCount();

        List<Notice> noticeBoards = boardMapper.searchNoticeBoard(
                startIndex,
                noticeBoardListReqDto.getCount(),
                noticeBoardListReqDto.getSearchText()
        );

        return noticeBoards.stream().map(Notice :: toNoticeBoardListRespDto).collect(Collectors.toList());
    }

    public NoticeCountRespDto getNoticeCount(NoticeBoardListReqDto noticeBoardListReqDto){
        int noticeCount = boardMapper.getNoticeCount(
                noticeBoardListReqDto.getCount(),
                noticeBoardListReqDto.getSearchText()
        );

        int maxPageNumber = (int) Math.ceil(((double) noticeCount) / noticeBoardListReqDto.getCount());

        return NoticeCountRespDto.builder()
                .totalCount(noticeCount)
                .maxPageNumber(maxPageNumber)
                .build();
    }

    public NoticeBoardPageRespDto getSingleNoticeBoards(int noticeId){

        Notice noticeBoard = boardMapper.getSingleNoticeBoard(noticeId);

        return noticeBoard.toNoticeBoardPageRespDto();
    }
}
