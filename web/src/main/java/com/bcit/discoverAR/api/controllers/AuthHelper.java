package com.bcit.discoverAR.api.controllers;

import com.bcit.discoverAR.auth.JwtTokenProvider;
import com.bcit.discoverAR.models.ApplicationUser;
import com.bcit.discoverAR.services.SpringDataJpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AuthHelper {
    @Autowired
    private SpringDataJpaUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    public Boolean isUserValid(String accessToken) {
        String oldToken = parseAccessToken(accessToken);
        return StringUtils.hasText(oldToken) && tokenProvider.validateToken(oldToken);
    }

    public UserDetails getUserDetails(String accessToken) {
        String oldToken = parseAccessToken(accessToken);
        Long userId = tokenProvider.getUserIdFromJWT(oldToken);

        UserDetails userDetails = customUserDetailsService.loadUserById(userId);
        return userDetails;
    }

    public ApplicationUser getUser(String accessToken) {
        String oldToken = parseAccessToken(accessToken);
        Long userId = tokenProvider.getUserIdFromJWT(oldToken);

        return customUserDetailsService.getCurrentUser(userId);
    }

    private String parseAccessToken(String accessToken) {
        return accessToken.substring(7);
    }
}
