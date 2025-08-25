package com.project.eventmanagement.services.impl;

import com.project.eventmanagement.dto.LoginRequestDTO;
import com.project.eventmanagement.dto.LoginResponseDTO;
import com.project.eventmanagement.model.CustomUserDetails;
import com.project.eventmanagement.model.User;
import com.project.eventmanagement.services.AuthService;
import com.project.eventmanagement.utility.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        Authentication authentication = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(),loginRequestDTO.getPassword())

        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();

        String token = authUtil.getAccessToken(user);

        return new LoginResponseDTO(token , user.getEmail(),user.getUserId());

    }
}
