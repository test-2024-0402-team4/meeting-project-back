package com.meeteam4.meeting.security.handler;


import com.meeteam4.meeting.entity.User;
import com.meeteam4.meeting.jwt.JwtProvider;
import com.meeteam4.meeting.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Value("${client.deploy-address}")
    private String clientAddress;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String name = authentication.getName();
        User user = userMapper.findByUsername(name);

        if(user == null) {
            DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
            String providerName = oAuth2User.getAttribute("provider").toString();

            response.sendRedirect("http://" + clientAddress + "/auth/oauth2?name=" + name + "&provider=" + providerName);
            return;
        }

        String accessToken = jwtProvider.generateToken(user);
        response.sendRedirect("http://" + clientAddress + "/auth/oauth2/signin?accessToken=" + accessToken);
    }
}
