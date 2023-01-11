package ru.parsing.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestParsingController {

    @RequestMapping(value = "/{questName}")
    ResponseEntity<String> Response(@PathVariable String operName) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type","application/json; charset=utf-8");
        return new ResponseEntity<>("HttpStatus.OK",httpHeaders, HttpStatus.OK);
    }
}
