package ru.parsing.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.parsing.dto.Photos;
import ru.parsing.repository.PhotoEntityRepositry;

public class PhotoServiceImpl implements PhotoService{
    @Autowired
    private PhotoEntityRepositry photoEntityRepositry;

    @Override
    public void save(Photos photos) {
        photoEntityRepositry.save(photos);
    }
}
