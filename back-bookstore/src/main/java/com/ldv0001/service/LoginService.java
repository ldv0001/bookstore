package com.ldv0001.service;

import com.ldv0001.model.AuthenticationResponse;
import com.ldv0001.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    final UserDetailsServiceImpl userDetailService;

    final JwtUtil jwtUtil;

    public LoginService(UserDetailsServiceImpl userDetailService, JwtUtil jwtUtil) {
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationResponse login(UserDetails userDetails)  {
        User user=(User)userDetailService.loadUserByUsername(userDetails.getUsername());
        AuthenticationResponse response= jwtUtil.generateToken(user);
        return response;
    }

}
