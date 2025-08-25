package com.project.eventmanagement.services;

import com.project.eventmanagement.dto.LoginRequestDTO;
import com.project.eventmanagement.dto.LoginResponseDTO;
import jakarta.validation.Valid;

public interface AuthService {


    LoginResponseDTO login(@Valid LoginRequestDTO user);
}
