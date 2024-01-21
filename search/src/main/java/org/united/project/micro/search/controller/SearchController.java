package org.united.project.micro.search.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/search")
public class SearchController {

    @GetMapping
    public ResponseEntity<String> solve(@RequestParam String query) {
        return new ResponseEntity<>(query, HttpStatus.OK);
    }
}
