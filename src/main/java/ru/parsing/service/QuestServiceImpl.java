package ru.parsing.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.parsing.dto.QuestDtoOnce;
import ru.parsing.repository.QuestRepository;

public class QuestServiceImpl implements QuestServices {
    @Autowired
    private QuestRepository questRepository;

    @Override
    public void save(QuestDtoOnce quest) {
        questRepository.save(quest);
    }

    @Override
    public QuestDtoOnce findByName(String name) {
        return questRepository.findByName(name);

    }
}
