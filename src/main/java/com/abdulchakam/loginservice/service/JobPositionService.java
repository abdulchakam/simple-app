package com.abdulchakam.loginservice.service;


import com.abdulchakam.loginservice.dto.JobPositionResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobPositionService {
    ResponseEntity<List<JobPositionResponseDto>> getAll() throws JsonProcessingException;
    ResponseEntity<JobPositionResponseDto> getById(String id) throws JsonProcessingException;
}
