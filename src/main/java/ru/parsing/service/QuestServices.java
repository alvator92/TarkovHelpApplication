package ru.parsing.service;

import org.springframework.transaction.annotation.Transactional;
import ru.parsing.dto.QuestDtoOnce;

public interface QuestServices {
    @Transactional
    void save(QuestDtoOnce quest);

    QuestDtoOnce findByName(String name);
}
