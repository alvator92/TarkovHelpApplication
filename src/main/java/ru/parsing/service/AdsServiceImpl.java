package ru.parsing.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.parsing.dto.Ads;
import ru.parsing.repository.AdsRepository;

import java.util.List;
import java.util.Optional;

public class AdsServiceImpl implements AdsService{

    @Autowired
    private AdsRepository adsRepository;

    @Override
    public void save(Ads advertising) {
        adsRepository.save(advertising);
    }

    @Override
    public Optional<Ads> findById(long id) {
        Optional<Ads> adsResponse = adsRepository.findById(id);
        return adsResponse;
    }

    @Override
    public List<Ads> findAll() {
        List<Ads> adsList = adsRepository.findAll();
        return adsList;
    }
}
