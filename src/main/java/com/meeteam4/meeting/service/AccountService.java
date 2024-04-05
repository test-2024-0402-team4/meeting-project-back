package com.meeteam4.meeting.service;


import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.repository.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public Student getStudentInfo(int studentId) {

       return accountMapper.findStudentByStudentId(studentId);
    }











}
