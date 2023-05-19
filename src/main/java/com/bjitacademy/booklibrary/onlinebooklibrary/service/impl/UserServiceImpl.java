package com.bjitacademy.booklibrary.onlinebooklibrary.service.impl;

import com.bjitacademy.booklibrary.onlinebooklibrary.entity.Role;
import com.bjitacademy.booklibrary.onlinebooklibrary.entity.User;
import com.bjitacademy.booklibrary.onlinebooklibrary.exception.PasswordNotmatchException;
import com.bjitacademy.booklibrary.onlinebooklibrary.exception.RegisterException;
import com.bjitacademy.booklibrary.onlinebooklibrary.exception.RoleNotFoundException;
import com.bjitacademy.booklibrary.onlinebooklibrary.exception.UserAlreadyExistException;
import com.bjitacademy.booklibrary.onlinebooklibrary.model.AuthenticationRequestModel;
import com.bjitacademy.booklibrary.onlinebooklibrary.model.AuthenticationResponse;
import com.bjitacademy.booklibrary.onlinebooklibrary.model.UserRequestModel;
import com.bjitacademy.booklibrary.onlinebooklibrary.repository.UserRepository;
import com.bjitacademy.booklibrary.onlinebooklibrary.service.RoleService;
import com.bjitacademy.booklibrary.onlinebooklibrary.service.UserService;
import com.bjitacademy.booklibrary.onlinebooklibrary.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final RoleService roleService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    @Override
    public String registerUser(UserRequestModel userRequestModel) {
        Optional<User> user = userRepo.findByEmail(userRequestModel.getEmail());

        if(user.isPresent()){
            throw new UserAlreadyExistException("User already exists");
        }

        List<String> roles = userRequestModel.getRoles();
        List<Role> toBeAddedRoles = new ArrayList<>();
        for(String role: roles){
            Role requiredRole = roleService.getRoles(role);
            if(requiredRole != null){
                toBeAddedRoles.add(requiredRole);
            }
            else{
                throw new RoleNotFoundException("No such roles");
            }

        }

        if(userRequestModel != null){
            User requiredUser = User.builder()
                    .firstName(userRequestModel.getFirstName())
                    .lastName(userRequestModel.getLastName())
                    .email(userRequestModel.getEmail())
                    .password(passwordEncoder.encode(userRequestModel.getPassword()))
                    .address(userRequestModel.getAddress())
                    .roles(toBeAddedRoles).build();
            User savedUser = userRepo.save(requiredUser);

//                     AuthenticationResponse authRes = AuthenticationResponse.builder()
//                    .token(jwtService.generateToken(savedUser))
//                    .build();
            return "User is created";
        }
        else{
            throw new RegisterException("All the fields are not entered");
        }

    }
    @Override
    public AuthenticationResponse login(AuthenticationRequestModel requestModel){
        validatePassword(requestModel);
        authManager.authenticate(new UsernamePasswordAuthenticationToken(requestModel.getEmail(), requestModel.getPassword()));
        Optional<User> user = userRepo.findByEmail(requestModel.getEmail());

        if(user.isPresent()){
            User requiredUser = user.get();

            var jwtToken = jwtService.generateToken(requiredUser);
            return AuthenticationResponse.builder()
                    .token(jwtToken).build();
        }

        else{
            throw new UsernameNotFoundException("Not found");
        }
    }

    //TO CHECK PASSWORD MATCHING, ISSUE IS CALLS TO DATABASE TWICE IN "LOGIN" method.
    public void validatePassword(AuthenticationRequestModel requestModel){
        Optional<User> user = userRepo.findByEmail(requestModel.getEmail());
        String password = requestModel.getPassword();

        if(user.isPresent()){
            User rUser = user.get();
            if(!passwordEncoder.matches(password, rUser.getPassword())){
                throw new PasswordNotmatchException("Password does not match");
            }
            else{
                return;
            }
        }
    }
}
