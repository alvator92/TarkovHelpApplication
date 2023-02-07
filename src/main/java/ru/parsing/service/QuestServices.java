package ru.parsing.service;

import org.springframework.transaction.annotation.Transactional;
import ru.parsing.dto.QuestDtoOnce;

import java.util.List;

public interface QuestServices {
    @Transactional
    void save(QuestDtoOnce quest);

    QuestDtoOnce findByName(String name);

    List<String> findQuestsNameByTrader(String traderName);
}
