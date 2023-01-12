package ru.parsing.service;

import org.jsoup.Jsoup;
import java.util.Map;

public class TradersQuests {
    public Map<String, String> getListOfQuests(String traderName) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("https://tarkov.help/ru/trader/");
            sb.append(traderName);
            sb.append("/quests");

            var document = Jsoup.connect(sb.toString()).get();

            var titleElements = document.select(".article__title");
            titleElements.forEach(titleElement ->
                    System.out.println(titleElement.text() + " | " + titleElement.attr("href")));


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
