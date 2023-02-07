package ru.parsing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.parsing.configuration.JpaConfig;
import ru.parsing.dto.Images;
import ru.parsing.dto.QuestDtoOnce;
import ru.parsing.emun.TradersEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class QuestController {
    @Autowired
    private JpaConfig config;
    public QuestDtoOnce saveNewQuest(String questName, String questUrl, String trader) {
        QuestDtoOnce questDtoOnce = new QuestClient().getQuestParam(questName, questUrl, trader);
        saveQuestToDB(questDtoOnce);

        List<Images> photos = new QuestImages().getImage(questDtoOnce);
        photos.forEach(this::saveImagesToDB);
        return questDtoOnce;
    }
    public void saveQuestToDB(QuestDtoOnce questDtoOnce) {
        config.questService().save(questDtoOnce);
    }

    public void saveImagesToDB(Images images) {
        config.photoService().save(images);
    }

    public void saveAllQuestToDB() {
        for (TradersEnum value : TradersEnum.values()) {
            HashMap<String, String> quests = new QuestTradersService().
                    getListOfQuests(value.getName());
            for (Map.Entry<String, String> entry : quests.entrySet()) {
                saveNewQuest(entry.getKey(), entry.getValue(), value.getUserName());
            }
        }
    }

    public QuestDtoOnce findQuestByName(String name) {
        return config.questService().findByName(name);
    }
}
