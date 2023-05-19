package com.bjitacademy.booklibrary.onlinebooklibrary.service;

import com.bjitacademy.booklibrary.onlinebooklibrary.model.AuthenticationRequestModel;
import com.bjitacademy.booklibrary.onlinebooklibrary.model.AuthenticationResponse;
import com.bjitacademy.booklibrary.onlinebooklibrary.model.UserRequestModel;
import com.bjitacademy.booklibrary.onlinebooklibrary.model.UserResponseModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String registerUser(UserRequestModel userRequestModel);
    AuthenticationResponse login(AuthenticationRequestModel requestModel);
}
