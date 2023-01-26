package ru.parsing.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "photos", schema = "public")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "quest_id")
    private QuestDtoOnce quest;

    public static class Builder {
        private Images images;

        public Builder() {
            images = new Images();
        }

        public Builder withPhoto(String var) {
            images.photo = var;
            return this;
        }

        public Builder withQuest(QuestDtoOnce var) {
            images.quest = var;
            return this;
        }

        public Images build() {
            return images;
        }
    }

}
