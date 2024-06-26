package com.meeteam4.meeting.jwt;


import com.meeteam4.meeting.entity.User;
import com.meeteam4.meeting.repository.UserMapper;
import com.meeteam4.meeting.security.PrincipalUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    private final Key key;
    private UserMapper userMapper;

    public JwtProvider(@Value("${jwt.secret}") String secret, @Autowired UserMapper userMapper) {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.userMapper = userMapper;
    }

    // Token에 담을 데이터 정보 설정 및 Token 생성
    public String generateToken(User user) {
        int userId = user.getUserId();
        int roleId = user.getRoleId();
        int studentId = user.getStudent().getStudentId();
        int teacherId = user.getTeacher().getTeacherId();
        int emailAuth = user.getEmailAuth();

        String username = user.getUsername();
        String email = user.getEmail();
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // 토큰 유효기간
        // 1000(1밀리초) * 60(60초) * 60(60분) * 24(24시간) * 20일(일)
        Date expireDate = new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 20));

        String accessToken = Jwts.builder()
                .claim("userId", userId)
                .claim("username", username)
                .claim("teacherId", teacherId)
                .claim("studentId", studentId)
                .claim("roleId", roleId)
                .claim("email", email)
                .claim("authorities", authorities)
                .claim("emailAuth", emailAuth)
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return accessToken;
    }

    public Authentication getAuthentication(Claims claims) {
        String username = claims.get("username").toString();
        User user = userMapper.findByUsername(username);

        PrincipalUser principalUser = user.toprincipalUser();
        System.out.println(principalUser);
        return new UsernamePasswordAuthenticationToken(principalUser, principalUser.getPassword(), principalUser.getAuthorities());
    }

    public String removeBearer(String token) {

        if(!StringUtils.hasText(token)) {
            return null;
        }
        return token.substring("Bearer ".length());
    }

    public Claims getClaims(String token) {
        Claims claims = null;
        claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
    public String generateAuthMailToken(int userId ,String toMailAddress) {
        Date expireDate = new Date(new Date().getTime() + (1000 * 60 * 3)); // 3분동안 유효한 토큰 시간 설정
        return Jwts.builder()
                .claim("userId", userId)
                .claim("toMailAddress", toMailAddress)
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
