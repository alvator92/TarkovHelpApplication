package ru.parsing.dto;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

public class QuestsDto implements Serializable {
    @XmlElement(name = "Quest")
    private List<QuestOneDto> quest;

    public List<QuestOneDto> getQuest() {
        return quest;
    }

    @Override
    public String toString() {
        return "To Complete Quest you need : {" +
        quest + "}";
    }
}
