package ru.parsing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.parsing.configuration.JpaConfig;
import ru.parsing.dto.QuestDtoOnce;
import ru.parsing.repository.QuestEntityRepository;

import java.util.HashMap;
import java.util.Map;

@Component
public class QuestController {
    @Autowired
    private JpaConfig config;

    public void saveQuestToDB(QuestDtoOnce questDtoOnce) {
        config.questService().save(questDtoOnce);
    }

    public void saveAllQuestToDB(String traderName) {
        QuestTradersService questTradersService = new QuestTradersService();
        HashMap<String, String> quests = questTradersService.getListOfQuests(traderName);
        for (Map.Entry<String, String> entry : quests.entrySet()) {
            config.questService().save(new QuestClient().getQuestParam(entry.getKey(), entry.getValue()));

        }
    }
}
