package ru.parsing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.parsing.dto.Ads;

public interface AdsRepository extends JpaRepository<Ads, Long> {

}
