package ru.parsing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.parsing.dto.Photos;

public interface PhotoEntityRepositry extends JpaRepository<Photos, Long> {

}
