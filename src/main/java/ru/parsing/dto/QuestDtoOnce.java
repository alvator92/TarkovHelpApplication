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
@Table(name = "quest", schema = "public")
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

    public String toString() {
        return "Для квеста : " + name +
                " тебе необходимо : {" + goal + "\n" +
                "в награду ты получишь : " + award + "\n" +
                "для выполнения квеста сделай следующее : " + complete + "\n" +
                "тебе пригодится : " + necessary + "}";
     }
}
