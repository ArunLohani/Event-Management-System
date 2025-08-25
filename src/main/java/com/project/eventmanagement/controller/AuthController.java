package com.project.eventmanagement.controller;


import com.project.eventmanagement.dto.AddUserDTO;
import com.project.eventmanagement.dto.LoginRequestDTO;
import com.project.eventmanagement.dto.LoginResponseDTO;
import com.project.eventmanagement.dto.UserDTO;
import com.project.eventmanagement.services.impl.AuthServiceImpl;
import com.project.eventmanagement.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;
    private final AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody AddUserDTO user){

        return new ResponseEntity<>(userService.saveNewUser(user), HttpStatus.CREATED);


    }

    @PostMapping("/add-admin")
    public ResponseEntity<UserDTO> addAdmin(@RequestBody AddUserDTO user){

        return new ResponseEntity<>(userService.saveNewAdmin(user), HttpStatus.CREATED);


    }



    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody @Valid LoginRequestDTO user){


        return new ResponseEntity<LoginResponseDTO>(authService.login(user),HttpStatus.OK);


    }


}
