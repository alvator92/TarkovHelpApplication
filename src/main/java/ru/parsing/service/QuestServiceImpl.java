package ru.parsing.service;

import liquibase.pro.packaged.A;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.parsing.dto.QuestDtoOnce;
import ru.parsing.repository.QuestEntityRepository;

public class QuestServiceImpl implements QuestServices {
    @Autowired
    private QuestEntityRepository questEntityRepository;

    @Override
    public void save(QuestDtoOnce quest) {
        questEntityRepository.save(quest);
    }

    @Override
    public QuestDtoOnce findByName(String name) {
        return questEntityRepository.findByName(name);

    }
}
