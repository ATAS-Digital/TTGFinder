package ru.atas.TRPfinder.Repositories;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.atas.TRPfinder.Entities.EventRegistration;
import ru.atas.TRPfinder.Entities.EventRegistrationId;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;


public interface EventRegistrationRepository extends CrudRepository<EventRegistration, EventRegistrationId> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO EVENT_REGISTRATIONS VALUES (?1, ?2, ?3)", nativeQuery = true)
    void addNewGame(Long playerId, Long gameId, String role);

    @Transactional
    @Query(value = "SELECT * FROM EVENT_REGISTRATIONS\n" +
            "WHERE game_id = (?1)", nativeQuery = true)
    List<EventRegistration> GetGameRegistrations(Long gameId);

    List<EventRegistration> findAllByGameId(Long gameId);
}
