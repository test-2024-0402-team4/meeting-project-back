package com.meeteam4.meeting.repository;

import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.entity.Teacher;
import com.meeteam4.meeting.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public int saveUser(User user);
}
