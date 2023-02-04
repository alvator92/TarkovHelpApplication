package ru.parsing.service;

import org.springframework.transaction.annotation.Transactional;
import ru.parsing.dto.Ads;
import ru.parsing.dto.User;

import java.util.List;
import java.util.Optional;

public interface AdsService {

    @Transactional
    void save(Ads adverties);

    /**
     * Найти пользователя по chatId
     */
    Optional<Ads> findById(long id);

    /**
     * Найти всех пользователей
     */
    List<Ads> findAll();
}
