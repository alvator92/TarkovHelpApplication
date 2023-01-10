package ru.parsing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.parsing.dto.QuestDtoOnce;

public interface QuestEntityRepository extends JpaRepository<QuestDtoOnce, Long> {

}
