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

    String game_place;

    String description;

    public GameEvent(){}

    public GameEvent(Long id, ZonedDateTime game_date, String game_name, String game_place, String description){
        this.id = id;
        this.game_date = game_date;
        this.game_name = game_name;
        this.description = description;
        this.game_place =  game_place;
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

    public String getGamePlace() {return game_place;}

    public String getDescription() {
        return description;
    }
}
