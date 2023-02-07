package ru.parsing.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.parsing.dto.QuestDtoOnce;
import ru.parsing.service.QuestController;

import java.util.List;

@RestController
@Slf4j
public class QuestParsingController {
    @Autowired
    private QuestController questController;

    @RequestMapping(value = "/save")
    ResponseEntity<QuestDtoOnce> Response(@RequestParam(value = "questName") String questName,
                                    @RequestParam(value = "questUrl") String questUrl,
                                    @RequestParam(value = "trader") String trader) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type","application/json; charset=utf-8");
        log.info(questName);
        log.info(questUrl);
        return new ResponseEntity<>(questController.saveNewQuest(questName, questUrl, trader),httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/save/all")
    ResponseEntity<String> Response(@RequestParam(value = "traderName") String traderName) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type","application/json; charset=utf-8");

        questController.saveAllQuestToDB();

        return new ResponseEntity<>("HttpStatus.OK",httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/find")
    ResponseEntity<QuestDtoOnce> findQuestByName(@RequestParam(value = "questName") String name) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type","application/json; charset=utf-8");
        log.info(name);
        QuestDtoOnce quest = questController.findQuestByName(name);

        return new ResponseEntity<>(quest,httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/listOfQuest")
    ResponseEntity<List<String>> findQuestByTrader(@RequestParam(value = "trader") String name) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type","application/json; charset=utf-8");
        log.info(name);


        return new ResponseEntity<>(questController.findQuestsNameByTrader(name),httpHeaders, HttpStatus.OK);
    }
}
