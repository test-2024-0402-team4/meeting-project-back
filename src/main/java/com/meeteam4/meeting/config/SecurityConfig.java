package com.meeteam4.meeting.config;

import com.meeteam4.meeting.security.exception.AuthEntryPoint;
import com.meeteam4.meeting.security.filter.JwtAuthenticationFilter;
import com.meeteam4.meeting.security.filter.PermitAllFilter;
import com.meeteam4.meeting.security.handler.OAuth2SuccessHandler;
import com.meeteam4.meeting.service.OAuth2PrincipalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Autowired
    private OAuth2PrincipalUserService oAuth2PrincipalUserService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private PermitAllFilter permitAllFilter;

    @Autowired
    private OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/server/hc", "/server/env")
                .permitAll()
                .antMatchers("/auth/**")
                .permitAll()
                .antMatchers("/mail/**")
                .permitAll()
                .antMatchers("/account/**")
                .permitAll()
                .antMatchers("/study/**")
                .permitAll()
                .antMatchers("/notice/**")
                .permitAll()
                .antMatchers("/student/**")
                .hasAnyRole("USER_STUDENT", "USER_ADMIN")
                .antMatchers("/teacher/**")
                .hasAnyRole("USER_TEACHER", "USER_ADMIN")
                .antMatchers("/admin/**")
                .hasRole("USER_ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .addFilterAfter(permitAllFilter, LogoutFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .oauth2Login()
                .successHandler(oAuth2SuccessHandler)
                .userInfoEndpoint()
                .userService(oAuth2PrincipalUserService);
    }
}
