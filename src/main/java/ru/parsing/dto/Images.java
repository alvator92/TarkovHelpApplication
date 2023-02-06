package ru.parsing.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Lob
    @Column(name = "image")
    private byte[] image;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quest_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_quest_id"))
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

        public Builder withImage(byte[] var) {
            images.image = var;
            return this;
        }

        public Images build() {
            return images;
        }
    }

//    @Override
//    public String toString() {
//        return "Images{" +
//                "id=" + id +
//                ", photo='" + photo + '}';
//    }
}
