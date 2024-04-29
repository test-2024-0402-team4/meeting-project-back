package com.meeteam4.meeting.service;

import com.meeteam4.meeting.dto.EmailApplyReqDto;
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
import java.io.UnsupportedEncodingException;

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

    //선생님 프로필 주소 학생 이메일에 전송
    public void sendTeacherProfile(EmailTeacherProfileReqDto emailTeacherProfileReqDto) throws Exception {
        // 이메일을 보내기 위한 MimeMessage 객체 생성
        MimeMessage message = emailSender.createMimeMessage();

        String profileLink = "http://" + serverAddress + ":3000/student/tutor?userId=" + emailTeacherProfileReqDto.getUserId();

        Context context = new Context();
        context.setVariable("profileLink", profileLink);

        // 이메일 템플릿을 처리하여 이메일 내용을 생성
        String emailContent = templateEngine.process("teacher_profile", context);

        message.addRecipients(MimeMessage.RecipientType.TO, emailTeacherProfileReqDto.getEmail());

        message.setSubject("과외나무에서 선생님 프로필이 도착했습니다!");

        message.setText(emailContent, "utf-8", "html");

        message.setFrom(new InternetAddress(fromMailAddress, "과외나무"));

        emailSender.send(message);
    }

    public void sendApplyEmail(EmailApplyReqDto applyData) throws Exception {

        MimeMessage message = emailSender.createMimeMessage();
        Context context = new Context();

        context.setVariable("applyData", applyData);

        String emailContent = templateEngine.process("apply", context);

        message.addRecipients(MimeMessage.RecipientType.TO, applyData.getTeacherEmail());


        message.setSubject("과외나무에서 과외신청 메일이 도착했습니다!");
        message.setText(emailContent, "utf-8", "html");
        message.setFrom(new InternetAddress(fromMailAddress, "과외나무"));

        emailSender.send(message);
    }





}
