package ru.atas.TRPfinder.Repositories;

import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.atas.TRPfinder.Entities.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
//    @Transactional
//    @Modifying
//    @Query(value = "INSERT INTO PLAYER (ID, PLAYER_LOGIN)\n" +
//            "VALUES (?1, ?2)", nativeQuery = true)
//    void addNewPlayer(String name, String login);
}
