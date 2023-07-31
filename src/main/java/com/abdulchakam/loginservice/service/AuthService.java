package com.abdulchakam.loginservice.service;

import com.abdulchakam.loginservice.dto.LoginRequest;
import com.abdulchakam.loginservice.dto.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<LoginResponse> login(LoginRequest loginRequest);
}
