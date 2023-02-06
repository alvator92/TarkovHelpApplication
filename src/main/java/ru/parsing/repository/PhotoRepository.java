package ru.parsing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.parsing.dto.Images;

public interface PhotoRepository extends JpaRepository<Images, Long> {

}
