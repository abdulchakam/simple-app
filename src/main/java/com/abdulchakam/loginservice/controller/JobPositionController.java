package com.abdulchakam.loginservice.controller;

import com.abdulchakam.loginservice.dto.JobPositionResponseDto;
import com.abdulchakam.loginservice.service.JobPositionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobPositionController {

    @Autowired
    private JobPositionService jobPositionService;

    @GetMapping("/all")
    public ResponseEntity<List<JobPositionResponseDto>> getAllJob() throws JsonProcessingException {
        return jobPositionService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPositionResponseDto> getJobById(@PathVariable String id) throws JsonProcessingException {
        return jobPositionService.getById(id);
    }


}
