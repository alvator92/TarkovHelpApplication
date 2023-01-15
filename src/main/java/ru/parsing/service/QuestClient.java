package ru.parsing.service;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import ru.parsing.dto.QuestDtoOnce;

@Service
public class QuestClient {
    private QuestDtoOnce quest = new QuestDtoOnce();

    public QuestDtoOnce getQuestParam(String questName, String questUrl) {
        try {
            StringBuilder s = new StringBuilder();
            s.append("https://tarkov.help");
            s.append(questUrl);
            System.out.println(s);
            quest.setName(questName);
            var document = Jsoup.connect( s.toString()).get();

            var titleElements = document.select(".quest-description");
            quest.setName(titleElements.text());

            var titleElements2 = document.select(".quest-tab-grid");
            quest.setGoal(titleElements2.text());

            var titleElements3 = document.select(".quest-text-award");
            quest.setAward(titleElements3.text());

            var titleElements4 = document.select(".quest-guide");
            quest.setComplete(titleElements4.text());

            var titleElements5 = document.select(".bb-block .bb-table, .quest-page .bb-table");
            quest.setNecessary(titleElements5.select(".item-name").text());

            quest.setUrl(questUrl);
            System.out.println(quest);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return quest;
    }

    @Override
    public String toString() {
        return quest.toString();
    }
}
