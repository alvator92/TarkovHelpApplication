package ru.parsing.service;

import lombok.Data;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Data
public class QuestTradersService {
    private HashMap<String, String> quests = new HashMap<>();
    public HashMap<String, String> getListOfQuests(String traderName) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("https://tarkov.help/ru/trader/");
            sb.append(traderName);
            sb.append("/quests");

            var document = Jsoup.connect(sb.toString()).get();

            var titleElements = document.select(".article__title");
            titleElements.forEach(titleElement ->
                    quests.put(titleElement.text(),titleElement.attr("href")));


        } catch (Exception e) {
            e.printStackTrace();
        }

        return quests;
    }
}
