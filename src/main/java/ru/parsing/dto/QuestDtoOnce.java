package ru.parsing.dto;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
@Entity
@Data
@XmlRootElement(name = "Quest")
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestDtoOnce implements Serializable {
    String s = (char) 27 + "[32m";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Goal")
    private String goal;

    @XmlElement(name = "Reward")
    private String reward;

    @XmlElement(name = "Complete")
    private String complete;

    @XmlElement(name = "NecessaryItem")
    private String necessary;

    public String getNecessary() {
        return s + "Для квеста : " + name +
                " тебе необходимо : {" + goal + "\n" +
                "в награду ты получишь : " + reward + "\n" +
                "для выполнения квеста сделай следующее : " + complete + "\n" +
                "тебе пригодится : " + necessary + "}";
     }
}
