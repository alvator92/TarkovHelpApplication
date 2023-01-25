package ru.parsing.service;

import org.springframework.transaction.annotation.Transactional;
import ru.parsing.dto.Photos;
import ru.parsing.dto.QuestDtoOnce;

public interface PhotoService {

    @Transactional
    void save(Photos photos);
}
