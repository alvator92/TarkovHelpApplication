package ru.parsing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.parsing.dto.QuestDtoOnce;

@Repository
public interface QuestEntityRepository extends JpaRepository<QuestDtoOnce, Long> {

}
