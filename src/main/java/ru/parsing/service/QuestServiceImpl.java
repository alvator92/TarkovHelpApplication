package ru.parsing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.parsing.dto.QuestDtoOnce;
import ru.parsing.repository.QuestRepository;

import java.util.List;

public class QuestServiceImpl implements QuestServices {
    @Autowired
    private QuestRepository questRepository;

    @Override
    public void save(QuestDtoOnce quest) {
        questRepository.save(quest);
    }

    // @Transactional использовал чтобы избавиться от ошибки LazyInitializationException
    // quest.getPhotos().iterator() для того чтобы достать все значения
    @Override
    @Transactional
    public QuestDtoOnce findByName(String name) {
        QuestDtoOnce quest = questRepository.findByName(name);
        quest.getPhotos().iterator();
        return quest;

    }

    @Override
    public List<String> findQuestsNameByTrader(String traderName) {
        List<String> quest = questRepository.findQuestsNameByTrader(traderName);
        return quest;

    }
}
