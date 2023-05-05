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
    @Query(value = "INSERT INTO GAME_EVENT (GAME_DATE, GAME_NAME, GAME_PLACE, DESCRIPTION) \n"+
    "VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void addNewGame(ZonedDateTime time, String name, String place, String description);

    @Transactional
    @Query(value = "SELECT * FROM GAME_EVENT\n" +
            "WHERE GAME_NAME = (?1)", nativeQuery = true)
    GameEvent getGameByName(String name);
}
