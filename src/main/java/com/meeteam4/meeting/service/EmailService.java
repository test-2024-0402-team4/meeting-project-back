package com.meeteam4.meeting.service;

import com.meeteam4.meeting.dto.EmailTeacherProfileReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.address}")
    private String fromMailAddress;
    @Value("${server.deploy-address}")
    private String serverAddress;
    @Value("${server.port}")
    private String serverPort;

    public void sendTeacherProfile(EmailTeacherProfileReqDto teacherProfileReqDto) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        StringBuilder profileLink = new StringBuilder();
        profileLink.append("\"http://" + serverAddress + ":" + "3000" + "/student/tutor?userId=" + teacherProfileReqDto.getUserId() + "\"");

        message.addRecipients(MimeMessage.RecipientType.TO, teacherProfileReqDto.getEmail()); // 보낼 이메일 설정
        message.setSubject("과외나무에서 선생님 프로필이 도착했습니다!"); // 이메일 제목
        message.setText(setContext(profileLink.toString()), "utf-8", "html"); // 내용 설정(Template Process)

         message.setFrom(new InternetAddress(fromMailAddress, "과외나무"));

        emailSender.send(message); // 이메일 전송
    }

    private String setContext(String profileLink) { // 타임리프 설정하는 코드
        Context context = new Context();
        context.setVariable("profileLink", profileLink); // Template에 전달할 데이터설정
        return templateEngine.process("teacher_profile", context);
    }



}
