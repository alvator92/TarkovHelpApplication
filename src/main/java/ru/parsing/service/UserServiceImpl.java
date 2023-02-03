package ru.parsing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.parsing.dto.User;
import ru.parsing.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(long id) {
        Optional<User> userResponse =  userRepository.findById(id);
        return userResponse;

    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
