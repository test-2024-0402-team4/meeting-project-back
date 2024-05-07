package com.meeteam4.meeting.service;

import com.meeteam4.meeting.repository.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public void disableAccount(int userId) {
        adminMapper.disableAccount(userId);
    }

}
