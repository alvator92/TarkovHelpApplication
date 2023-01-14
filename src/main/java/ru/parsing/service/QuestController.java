package ru.parsing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.parsing.configuration.JpaConfig;
import ru.parsing.dto.QuestDtoOnce;
import ru.parsing.repository.QuestEntityRepository;

@Component
public class QuestController {
    @Autowired
    private JpaConfig config;

    public void saveQuestToDB(QuestDtoOnce questDtoOnce) {
        config.questService().save(questDtoOnce);
    }
}
