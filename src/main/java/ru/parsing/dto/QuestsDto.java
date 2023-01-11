package ru.parsing.dto;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

public class QuestsDto implements Serializable {
    @XmlElement(name = "Quests")
    private List<QuestDtoOnce> quest;

    public List<QuestDtoOnce> getQuest() {
        return quest;
    }

    @Override
    public String toString() {
        return "Quests : {" +
        quest + "}";
    }
}
