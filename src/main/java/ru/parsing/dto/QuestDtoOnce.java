package ru.parsing.dto;

import com.vdurmont.emoji.EmojiParser;
import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "quests", schema = "public")
@XmlRootElement(name = "Quest")
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestDtoOnce implements Serializable {

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

    @Column(name = "trader")
    private String trader;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quest", fetch = FetchType.LAZY)
    private List<Images> photos;


    public String toString() {
        return EmojiParser.parseToUnicode("ДЛЯ_КВЕСТА : " + name + ":blush:" + "\n\n" +
                "ОПИСАНИЕ_КВЕСТА : " + description + ":smirk:" + "\n\n" +
                "НЕОБХОДИМО : " + goal + ":key:" + "\n\n" +
                "В_НАГРАДУ : " + award + ":sunglasses:" + "\n\n" +
                "КАК_ВЫПОЛНИТЬ_КВЕСТ : " + complete + ":heavy_check_mark:" + "\n\n" +
                "ТЕБЕ_ПРИГОДИТСЯ : " + necessary + ":100:" + "\n\n");
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

        public Builder withTrader(String var) {
            quest.trader = var;
            return this;
        }

        public QuestDtoOnce build() {
            return quest;
        }
     }
}
