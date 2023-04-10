package ru.atas.TRPfinder.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.ZonedDateTime;

@Entity
@Table(name="game_event")
public class GameEvent {
    @Id
    @GeneratedValue
    Long id;

    ZonedDateTime game_date;

    String game_name;

    String description;

    public GameEvent(){}

    public GameEvent(Long id, ZonedDateTime game_date, String game_name, String description){
        this.id = id;
        this.game_date = game_date;
        this.game_name = game_name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public ZonedDateTime getDate() {
        return game_date;
    }

    public String getName() {
        return game_name;
    }

    public String getDescription() {
        return description;
    }
}
