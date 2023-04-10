package ru.atas.TRPfinder.Entities;

import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class EventRegistrationId implements Serializable {
    private Long player_id;
    private Long game_id;

    public EventRegistrationId(){}

    public EventRegistrationId(Long player_id, Long game_id){
        this.game_id = game_id;
        this.player_id = player_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventRegistrationId accountId = (EventRegistrationId) o;
        return game_id.equals(accountId.game_id) &&
                player_id.equals(accountId.player_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player_id, game_id);
    }
}
