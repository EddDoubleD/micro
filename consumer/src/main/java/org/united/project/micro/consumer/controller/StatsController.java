package org.united.project.micro.consumer.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/consumer")
public class StatsController {

    @GetMapping
    public ResponseEntity<String> solve(@RequestParam String name) {
        return new ResponseEntity<>(name, HttpStatus.OK);
    }
}
