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
@Table(name = "tarkovdb", schema = "public")
@XmlRootElement(name = "Quest")
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestDtoOnce implements Serializable {
    String s = (char) 27 + "[32m";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @XmlElement(name = "Name")
    @Column(name = "name", nullable = false)
    private String name;

    @XmlElement(name = "Goal")
    @Column(name = "goal", nullable = false)
    private String goal;

    @XmlElement(name = "Award")
    @Column(name = "award", nullable = false)
    private String award;

    @XmlElement(name = "Complete")
    @Column(name = "complete", nullable = false)
    private String complete;

    @XmlElement(name = "NecessaryItem")
    @Column(name = "necessary", nullable = false)
    private String necessary;

    @Column(name = "url", nullable = false)
    private String url;

    public String toString() {
        return s + "Для квеста : " + name +
                " тебе необходимо : {" + goal + "\n" +
                "в награду ты получишь : " + award + "\n" +
                "для выполнения квеста сделай следующее : " + complete + "\n" +
                "тебе пригодится : " + necessary + "}";
     }
}
