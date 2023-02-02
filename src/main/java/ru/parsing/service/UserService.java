package ru.parsing.service;

import org.springframework.transaction.annotation.Transactional;
import ru.parsing.dto.User;

import java.util.Optional;

public interface UserService {

    @Transactional
    void save(User user);

    User getById(long id);
}
