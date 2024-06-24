package com.ldv0001.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("expires_date")
    private long expiresDate;
    @JsonProperty("role")
    private String role;
    @JsonProperty("user")
    private String user;

    public AuthenticationResponse(String accessToken,
                                  String refreshToken,
                                  long expiresDate,
                                  String role,
                                  String user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresDate = expiresDate;
        this.role = role;
        this.user = user;
    }
}
