package ru.parsing.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.parsing.dto.Images;
import ru.parsing.dto.QuestDtoOnce;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class FindQuestService {

    @Autowired
    private QuestController questController;
    @Autowired
    private ExecutionService executionService;

    public void findQuestByName(long chatId, String questName) {
        QuestDtoOnce quest = questController.findQuestByName(questName);
        executionService.prepareAndSendMessage(chatId, quest.toString());
        getImageFromQuest(chatId, quest);
    }

    public void getImageFromQuest(long chatId, QuestDtoOnce questDtoOnce) {
        List<Images> listPhotos = questDtoOnce.getPhotos();
        listPhotos.forEach(element -> {
            executionService.prepareAndSendMessage(chatId, new ByteArrayInputStream(element.getImage()), questDtoOnce.getName());
        });
    }

}
