package com.meeteam4.meeting.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {

    public void disableAccount(@Param("userId") int userId);

}
