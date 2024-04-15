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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;

@Component
public class JwtProvider {

    private final Key key;
    private UserMapper userMapper;

    public JwtProvider(@Value("${jwt.secret}") String secret, @Autowired UserMapper userMapper) {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.userMapper = userMapper;
    }

    public String generateToken(User user) {
        int userId = user.getUserId();
        int roleId = user.getRoleId();
        String username = user.getUsername();
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        Date expireDate = new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 20));

        String accessToken = Jwts.builder()
                .claim("userId", userId)
                .claim("username", username)
                .claim("roleId", roleId)
                .claim("authorities", authorities)
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return accessToken;
    }

    public Authentication getAuthentication(Claims claims) {
        String username = claims.get("username").toString();
        User user = userMapper.findByUsername(username);

        if(user == null) {
            return null;
        }
        PrincipalUser principalUser = user.toprincipalUser();
        return new UsernamePasswordAuthenticationToken(principalUser, principalUser.getPassword(), principalUser.getAuthorities());
    }
}
