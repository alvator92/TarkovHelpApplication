package ru.parsing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.parsing.service.QuestClient;
import ru.parsing.service.QuestController;

@RestController
public class QuestParsingController {
    @Autowired
    private QuestController questController;

    @RequestMapping(value = "/save")
    ResponseEntity<String> Response(@RequestParam(value = "questName") String questName,
                                    @RequestParam(value = "questUrl") String questUrl) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type","application/json; charset=utf-8");
        System.out.println(questName);
        System.out.println(questUrl);

        questController.saveQuestToDB(new QuestClient().getQuestParam(questName, questUrl));
        return new ResponseEntity<>("HttpStatus.OK",httpHeaders, HttpStatus.OK);
    }
}
