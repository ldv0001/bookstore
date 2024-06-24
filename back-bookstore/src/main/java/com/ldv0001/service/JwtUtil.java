package com.ldv0001.service;

import com.ldv0001.model.AuthenticationResponse;
import com.ldv0001.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {


    final UserDetailsServiceImpl userDetailService;

    private final String SECRET_KEY="0d111108a13301a13b3ed45fe66d581596cbf132dfcd88348982bb47c106bfdc";

    public JwtUtil(UserDetailsServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }

    public AuthenticationResponse generateToken(User user){

        long expiresDate = System.currentTimeMillis()+60*10000;

        long expiresDateRefreshToken = System.currentTimeMillis()+864*1000*100;

        Map<String,Object> claims = new HashMap<>();
        claims.put("role",user.getRole());

        String accessToken = Jwts.builder()
                .subject(user.getUsername())
                .signWith(secretKey())
                .claims(claims)
                .expiration(new Date(expiresDate))
                .compact();

        String refreshToken = Jwts.builder()
                .subject(user.getUsername())
                .signWith(secretKey())
                .expiration(new Date(expiresDateRefreshToken))
                .compact();
        AuthenticationResponse response = new AuthenticationResponse(accessToken,
                refreshToken,
                expiresDate,
                user.getRole().toString(),
                user.getUsername());
        return  response;
    }

    public SecretKey secretKey(){
        byte[]keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        return key;
    }

    public String extractUsername(String token){
        Claims claims = Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String username = claims.getSubject();
        return username;
    }
    public boolean validationToken(String token){
        JwtParser jwtParser = Jwts.parser()
                .verifyWith(secretKey())
                .build();
        try {
            jwtParser.parse(token);
        }catch (Exception e){
            throw new BadCredentialsException("validation exception");
        }
        return  true;
    }

    public AuthenticationResponse refreshToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(authHeader==null||!authHeader.startsWith("Bearer")){
            throw new BadCredentialsException("validation exception");
        }
        long expiresDate = System.currentTimeMillis()+60*10000;
        String refreshToken = authHeader.substring("Bearer ".length());
        String username =extractUsername(refreshToken);
        User user =(User)userDetailService.loadUserByUsername(username);

        Map<String,Object> claims = new HashMap<>();
        claims.put("role",user.getRole());

        String accessToken = Jwts.builder()
                .subject(user.getUsername())
                .signWith(secretKey())
                .claims(claims)
                .expiration(new Date(expiresDate))
                .compact();
        AuthenticationResponse response = new AuthenticationResponse(accessToken,
                refreshToken,
                expiresDate,
                user.getRole(),
                user.getUsername());
        return response;
    }

}
