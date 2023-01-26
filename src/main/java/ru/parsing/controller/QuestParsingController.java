package ru.parsing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.parsing.dto.Images;
import ru.parsing.dto.QuestDtoOnce;
import ru.parsing.service.QuestClient;
import ru.parsing.service.QuestController;
import ru.parsing.service.QuestImages;

import java.util.List;

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

        QuestDtoOnce questDtoOnce = new QuestClient().getQuestParam(questName, questUrl);
        questController.saveQuestToDB(questDtoOnce);

        List<Images> photos = new QuestImages().getImage(questDtoOnce);
        photos.forEach(photo -> questController.saveImagesToDB(photo));

        return new ResponseEntity<>(questDtoOnce.toString(),httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/save/all")
    ResponseEntity<String> Response(@RequestParam(value = "traderName") String traderName) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type","application/json; charset=utf-8");

        questController.saveAllQuestToDB();

        return new ResponseEntity<>("HttpStatus.OK",httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/find")
    ResponseEntity<String> findQuestByName(@RequestParam(value = "questName") String name) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type","application/json; charset=utf-8");
        System.out.println(name);
        QuestDtoOnce quest = questController.findQuestByName(name);

        return new ResponseEntity<>(quest.toString(),httpHeaders, HttpStatus.OK);
    }
}
