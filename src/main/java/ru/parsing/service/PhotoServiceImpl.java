package ru.parsing.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.parsing.dto.Images;
import ru.parsing.repository.PhotoEntityRepositry;

public class PhotoServiceImpl implements PhotoService{
    @Autowired
    private PhotoEntityRepositry photoEntityRepositry;

    @Override
    public void save(Images images) {
        photoEntityRepositry.save(images);
    }
}
