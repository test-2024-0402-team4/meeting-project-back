package com.meeteam4.meeting.service;


import com.meeteam4.meeting.entity.*;
import com.meeteam4.meeting.repository.AuthOptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
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

    public List<Subject> getSubject() {
        return authOptionMapper.getSubject();
    }

    public List<ClassType> getClassType() {
        return authOptionMapper.getClassType();
    }
    public List<Date> getDate() {
        return authOptionMapper.getDate();
    }
}
