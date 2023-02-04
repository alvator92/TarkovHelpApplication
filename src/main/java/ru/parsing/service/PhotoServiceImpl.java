package ru.parsing.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.parsing.dto.Images;
import ru.parsing.repository.PhotoRepository;

public class PhotoServiceImpl implements PhotoService{
    @Autowired
    private PhotoRepository photoRepositry;

    @Override
    public void save(Images images) {
        photoRepositry.save(images);
    }
}
