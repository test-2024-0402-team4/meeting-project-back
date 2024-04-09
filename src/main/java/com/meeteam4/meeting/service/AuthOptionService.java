package com.meeteam4.meeting.service;


import com.meeteam4.meeting.entity.GraduateState;
import com.meeteam4.meeting.entity.Region;
import com.meeteam4.meeting.entity.StudentType;
import com.meeteam4.meeting.entity.University;
import com.meeteam4.meeting.repository.AuthOptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthOptionService {

    @Autowired
    private AuthOptionMapper authOptionMapper;

    public List<Region> getRegion() {
        return authOptionMapper.getRegion();
    }

    public List<StudentType> getStudentType() {
        return authOptionMapper.getStudentType();
    }

    public List<University> getUniversity() {
        return authOptionMapper.getUniversity();
    }

    public List<GraduateState> getGraduateState() {
        return authOptionMapper.getGraduateState();
    }
}
