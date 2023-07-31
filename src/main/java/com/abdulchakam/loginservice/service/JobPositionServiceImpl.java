package com.abdulchakam.loginservice.service;

import com.abdulchakam.loginservice.dto.JobPositionResponseDto;
import com.abdulchakam.loginservice.exception.DataNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Service
@Slf4j
public class JobPositionServiceImpl implements JobPositionService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String RESPONSE_NOT_FOUND_MESSAGE = "Response data not found";

    @Override
    public ResponseEntity<List<JobPositionResponseDto>> getAll() throws JsonProcessingException {
        log.info("Star get all job");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String getAllJobUrl = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";

            ResponseEntity<String> response = restTemplate.exchange(getAllJobUrl, HttpMethod.GET, null, String.class);

            if (ObjectUtils.isEmpty(response.getBody())) {
                log.error(RESPONSE_NOT_FOUND_MESSAGE);
                throw new DataNotFoundException(RESPONSE_NOT_FOUND_MESSAGE);
            }

            String responseJson = response.getBody();
            List<JobPositionResponseDto> jobPositionResponseDtoList = Arrays.asList(objectMapper.readValue(responseJson, JobPositionResponseDto[].class));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(jobPositionResponseDtoList);

        } catch (Exception e) {
            log.error("Error when get all job");
            throw e;
        }
    }

    @Override
    public ResponseEntity<JobPositionResponseDto> getById(String id) throws JsonProcessingException {
        log.info("Star get job by id");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String getJobByIdUrl = "http://dev3.dansmultipro.co.id/api/recruitment/positions/";

            ResponseEntity<String> response = restTemplate.exchange(getJobByIdUrl + id, HttpMethod.GET, null, String.class);

            if (ObjectUtils.isEmpty(response.getBody())) {
                log.error(RESPONSE_NOT_FOUND_MESSAGE);
                throw new DataNotFoundException(RESPONSE_NOT_FOUND_MESSAGE);
            }

            String responseJson = response.getBody();
            JobPositionResponseDto jobPositionResponseDto = objectMapper.readValue(responseJson, JobPositionResponseDto.class);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(jobPositionResponseDto);

        } catch (Exception e) {
            log.error("Error when get job by id");
            throw e;
        }
    }
}
