package com.meeteam4.meeting.service;

import com.meeteam4.meeting.dto.EmailApplyReqDto;
import com.meeteam4.meeting.dto.EmailTeacherProfileReqDto;
import com.meeteam4.meeting.entity.User;
import com.meeteam4.meeting.jwt.JwtProvider;
import com.meeteam4.meeting.repository.UserMapper;
import com.meeteam4.meeting.security.PrincipalUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private JwtProvider jwtProvider;
    @Value("${spring.mail.address}")
    private String fromMailAddress;
    @Value("${server.deploy-address}")
    private String serverAddress;
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private UserMapper userMapper;

    // 인증메일 보내기
    public boolean sendAuthMail() throws MessagingException{
        boolean result = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        PrincipalUser principalUser =(PrincipalUser) authentication.getPrincipal();
        String toMailAddress = principalUser.getEmail();
        int userId = principalUser.getUserId();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setSubject("과외나무에서 보낸 이메일 인증메일이 도착했습니다!");
            helper.setFrom(new InternetAddress(fromMailAddress, "과외나무"));
            helper.setTo(toMailAddress);
            String authMailToken = jwtProvider.generateAuthMailToken(userId ,toMailAddress);
            String auth = "http://" + serverAddress + ":" + serverPort +  "/mail/authenticate?authToken=" + authMailToken;

            Context context = new Context();
            context.setVariable("auth", auth);

            String emailContent = templateEngine.process("auth", context);

            mimeMessage.setText(emailContent, "utf-8", "html");

            javaMailSender.send(mimeMessage); //메일 전송
            result = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    // 메일 인증
    public Map<String ,Object> authenticate(String token) {
        Claims claims = null;
        Map<String, Object> resultMap = null;
        System.out.println(token);
        // ExpiredJwtException => 토큰 유효기간 만료
        // MalformedJwtException => 위조, 변조
        // SignatureException => 형식, 길이 오류
        // IllegalArgumentException => null 또는 빈값
        try {
            claims = jwtProvider.getClaims(token);
            int userId = Integer.parseInt(claims.get("userId").toString());
            System.out.println(userId);
            User user = userMapper.findEmailAuthByUserId(userId); // DB 권한 중복확인
            if(user.getEmailAuth() == 2) {
                resultMap = Map.of("status", false, "message", "이미 인증된 메일입니다.");
                System.out.println(resultMap);
            } else {
                userMapper.updateEmailAuth(userId, 2);
                resultMap = Map.of("status", true, "message", "인증 완료.");
                System.out.println(resultMap);

            }
        } catch (ExpiredJwtException e) {
            resultMap = Map.of("status", false, "message", "인증 시간을 초과하였습니다 \n 인증 메일을 다시 받으세요.");
            System.out.println(resultMap);
        } catch (JwtException e) {
            resultMap = Map.of("status", false, "message", "인증 메일을 다시 받으세요. \n 인증 메일을 다시 받으세요.");
            System.out.println(resultMap);

        }


        return resultMap;
    }

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

    // 과외 신청 메일 보내기
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
