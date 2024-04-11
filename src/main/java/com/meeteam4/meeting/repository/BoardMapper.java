package com.meeteam4.meeting.repository;

import com.meeteam4.meeting.entity.StudentBoard;
import com.meeteam4.meeting.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    public int saveBoard(StudentBoard board);

    public int saveComment(Comment comment);

    public List<StudentBoard> searchBoard(
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("searchText") String searchText
    );

    public int getStudentCount(
            @Param("count") int count,
            @Param("searchText") String searchText
            );

    public StudentBoard getSingleBoard(int studentBoardId);

    public int deleteBoardByBoardId(int studentBoardId);

    public int updateBoardByBoardId(StudentBoard studentBoard);
}
