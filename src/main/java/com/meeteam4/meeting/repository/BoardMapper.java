package com.meeteam4.meeting.repository;

import com.meeteam4.meeting.entity.Board;
import com.meeteam4.meeting.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    public int saveBoard(Board board);

    public int saveComment(Comment comment);
}
