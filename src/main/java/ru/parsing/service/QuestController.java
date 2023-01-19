package ru.parsing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.parsing.configuration.JpaConfig;
import ru.parsing.dto.QuestDtoOnce;
import ru.parsing.emun.TradersEnum;

import java.util.HashMap;
import java.util.Map;

@Component
public class QuestController {
    @Autowired
    private JpaConfig config;

    public void saveQuestToDB(QuestDtoOnce questDtoOnce) {
        config.questService().save(questDtoOnce);
    }

    public void saveAllQuestToDB() {
        for (TradersEnum value : TradersEnum.values()) {
            HashMap<String, String> quests = new QuestTradersService().
                    getListOfQuests(value.getName());
            for (Map.Entry<String, String> entry : quests.entrySet()) {
                config.questService().save(new QuestClient().getQuestParam(entry.getKey(), entry.getValue()));
            }
        }
    }

    public QuestDtoOnce findQuestByName(String name) {
        return config.questService().findByName(name);
    }
}
