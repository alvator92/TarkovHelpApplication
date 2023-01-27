package ru.parsing.service;

import org.springframework.transaction.annotation.Transactional;
import ru.parsing.dto.Images;

public interface PhotoService {

    @Transactional
    void save(Images images);
}
