package com.bjitacademy.booklibrary.onlinebooklibrary.controller;

import com.bjitacademy.booklibrary.onlinebooklibrary.model.AuthenticationRequestModel;
import com.bjitacademy.booklibrary.onlinebooklibrary.model.UserRequestModel;
import com.bjitacademy.booklibrary.onlinebooklibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserRequestModel userRequestModel){
        return new ResponseEntity<>(userService.registerUser(userRequestModel), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequestModel requestModel){
        return new ResponseEntity<>(userService.login(requestModel),HttpStatus.OK);
    }
}
