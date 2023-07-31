package com.abdulchakam.loginservice.service;

import com.abdulchakam.loginservice.dto.LoginRequest;
import com.abdulchakam.loginservice.dto.LoginResponse;
import com.abdulchakam.loginservice.exception.DataNotFoundException;
import com.abdulchakam.loginservice.exception.IncorrectPasswordException;
import com.abdulchakam.loginservice.model.User;
import com.abdulchakam.loginservice.repository.UserRepository;
import com.abdulchakam.loginservice.util.BCryptUtil;
import com.abdulchakam.loginservice.util.jwt.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        log.info("Start login");
        HttpStatus status = HttpStatus.OK;

        try {
            User userExist = userRepository.findByUsername(request.getUsername());

            if (ObjectUtils.isEmpty(userExist)) {
                log.error("Username not found!");
                status = HttpStatus.NOT_FOUND;
                throw new DataNotFoundException("Username not found");
            }

            boolean isPasswordValid = BCryptUtil.checkPassword(request.getPassword(), userExist.getPassword());
            if (!isPasswordValid) {
                log.error("Incorrect password!");
                status = HttpStatus.BAD_REQUEST;
                throw new IncorrectPasswordException("The password you entered is incorrect");
            }

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            return ResponseEntity
                    .status(status)
                    .body(LoginResponse.builder()
                            .accessToken(jwtTokenUtil.generateToken(userExist))
                            .username(userExist.getUsername())
                            .build()
                    );

        } catch (Exception e) {
            log.error("Error when login");
            throw e;
        }
    }
}
