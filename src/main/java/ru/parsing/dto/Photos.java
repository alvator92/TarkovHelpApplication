package ru.parsing.dto;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Data
@Table(name = "photos", schema = "public")
public class Photos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "quest_id")
    private String quest_id;

    @Column(name = "photo")
    private String photo;

}
