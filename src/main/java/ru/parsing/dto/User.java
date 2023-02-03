package ru.parsing.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity(name = "users")
@Data
@Table(name = "users", schema = "public")
public class User {
    
    @Id
    @Column(name = "\"chatId\"")
    private Long chatId;
    @Column(name = "\"firstName\"")
    private String firstName;
    @Column(name = "\"lastName\"")
    private String lastName;
    @Column(name = "\"userName\"")
    private String userName;
    @Column(name = "\"registerAt\"")
    private Timestamp registerAt;
    
    public static class Builder {
        private User user;
        
        public Builder() {
            user = new User();
        }
        
        public Builder withChatId(Long val) {
            user.chatId = val;
            return this;
        }
        
        public Builder withFirstName(String val) {
            user.firstName = val;
            return this;
        }
        
        public Builder withLastName(String val) {
            user.lastName = val;
            return this;
        }
        
        public Builder withUserName(String val) {
            user.userName = val;
            return this;
        }

        public Builder withRegisterAt(Timestamp val) {
            user.registerAt = val;
            return this;
        }

        public User build() {
            return user;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "chatId=" + chatId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", registerAt=" + registerAt +
                '}';
    }
}
