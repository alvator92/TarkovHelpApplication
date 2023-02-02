package ru.parsing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.parsing.dto.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT q FROM users q WHERE q.chatId = :chatId")
    Optional<User> getUserById(@Param("chatId") Long chatId);
}
