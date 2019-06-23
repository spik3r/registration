package com.kaitait.registration.controller;

import com.kaitait.registration.documentation.HealthControllerDocumentation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Slf4j
public class HealthController implements HealthControllerDocumentation {

    @Override
    public ResponseEntity<String> health() {
        return new ResponseEntity("I'm alive :)", HttpStatus.OK);
    }
}
