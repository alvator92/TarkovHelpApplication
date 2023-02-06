package ru.parsing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.parsing.dto.QuestDtoOnce;

@Repository
public interface QuestRepository extends JpaRepository<QuestDtoOnce, Long> {

    @Query("SELECT q FROM QuestDtoOnce q WHERE q.name = :name")
    QuestDtoOnce findByName(@Param("name") String name);

}
