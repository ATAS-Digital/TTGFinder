package ru.atas.TRPfinder.Entities;

import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

@Component
public class EventRegistrationId implements Serializable {
    private Long playerId;
    private Long gameId;

    public EventRegistrationId(){}

    public EventRegistrationId(Long playerId, Long gameId){
        this.gameId = gameId;
        this.playerId = playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventRegistrationId accountId = (EventRegistrationId) o;
        return gameId.equals(accountId.gameId) &&
                playerId.equals(accountId.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, gameId);
    }
}
