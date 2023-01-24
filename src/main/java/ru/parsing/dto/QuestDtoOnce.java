package ru.parsing.dto;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "quests", schema = "public")
@XmlRootElement(name = "Quest")
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestDtoOnce implements Serializable {

    @OneToMany(mappedBy = "quests")
    private Set<Photos> photos;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @XmlElement(name = "Name")
    @Column(name = "name")
    private String name;

    @XmlElement(name = "Description")
    @Column(name = "description")
    private String description;

    @XmlElement(name = "Goal")
    @Column(name = "goal")
    private String goal;

    @XmlElement(name = "Award")
    @Column(name = "award", nullable = true)
    private String award;

    @XmlElement(name = "Complete")
    @Column(name = "complete")
    private String complete;

    @XmlElement(name = "NecessaryItem")
    @Column(name = "necessary")
    private String necessary;

    @Column(name = "url")
    private String url;

    @Column(name = "quest_id")
    private String quest_id;

    public String toString() {
        return "ДЛЯ_КВЕСТА : " + name + "\n" +
                "ОПИСАНИЕ_КВЕСТА : " + description + "\n" +
                "НЕОБХОДИМО : " + goal + "\n" +
                "В_НАГРАДУ : " + award + "\n" +
                "ДЛЯ_ВЫПОЛНЕНИЯ_НАДО : " + complete + "\n" +
                "ТЕБЕ_ПРИГОДИТСЯ : " + necessary + "}";
     }

     public static class Builder {
        private QuestDtoOnce quest;
        public Builder() {
            quest = new QuestDtoOnce();
        }
        public Builder withId(int var) {
            quest.id = var;
            return this;
        }
        public Builder withName(String var) {
            quest.name = var;
            return this;
        }
        public Builder withDescription(String var) {
            quest.description = var;
            return this;
        }
        public Builder withGoal(String var) {
            quest.goal = var;
            return this;
        }
        public Builder withAward(String var) {
            quest.award = var;
            return this;
        }
        public Builder withComplete(String var) {
            quest.complete = var;
            return this;
        }
        public Builder withNecessary(String var) {
            quest.necessary = var;
            return this;
        }
        public Builder withUrl(String var) {
            quest.url = var;
            return this;
        }
     }
}
