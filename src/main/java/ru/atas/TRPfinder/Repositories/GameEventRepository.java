package ru.atas.TRPfinder.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.atas.TRPfinder.Entities.GameEvent;

import java.time.ZonedDateTime;

public interface GameEventRepository extends CrudRepository<GameEvent, Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO GAME_EVENT (GAME_DATE, GAME_NAME, DESCRIPTION) \n"+
    "VALUES (?1, ?2, ?3)", nativeQuery = true)
    void addNewGame(ZonedDateTime time, String name, String description);
}
