package ru.parsing.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "photos", schema = "public")
public class Photos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "quest_id")
    private QuestDtoOnce quest;

}
