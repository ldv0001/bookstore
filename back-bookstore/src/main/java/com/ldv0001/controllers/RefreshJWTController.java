package com.ldv0001.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldv0001.Exceptions.TokenIsMissingException;
import com.ldv0001.model.User;
import com.ldv0001.repo.UserRepository;
import com.ldv0001.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
public class RefreshJWTController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response){

        String authHeader = request.getHeader(AUTHORIZATION);
        if(authHeader != null && authHeader.startsWith("Bearer ")){

            try {
                String token = authHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();
                User user = userRepository.findByUsername(username);
                UserDetailsImpl userDetails = new UserDetailsImpl(user);
                long EXPIRES_DATE = System.currentTimeMillis()+120*1000*100;

                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(EXPIRES_DATE))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",userDetails.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("role",userDetails.getRole());
                tokens.put("user",user.getUsername());
                tokens.put("expires_date",String.valueOf(EXPIRES_DATE));
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);

            }catch(Exception ex){
                ex.printStackTrace();
                response.setHeader("error",ex.getMessage());
            }
        }else {
            throw new TokenIsMissingException();
        }
    }

}
