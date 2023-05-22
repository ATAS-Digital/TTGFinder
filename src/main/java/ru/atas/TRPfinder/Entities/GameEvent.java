package ru.atas.TRPfinder.Entities;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name="game_event")
public class GameEvent {
    @Id
    @GeneratedValue
    Long id;

    @Column(name = "game_date")
    ZonedDateTime gameDate;

    @Column(name = "game_name")
    String gameName;

    @Column(name = "game_place")
    String gamePlace;

    @Column(name = "description")
    String description;

    public GameEvent(){}

    public GameEvent(Long id, ZonedDateTime gameDate, String gameName, String gamePlace, String description){
        this.id = id;
        this.gameDate = gameDate;
        this.gameName = gameName;
        this.description = description;
        this.gamePlace =  gamePlace;
    }

    public Long getId() {
        return id;
    }

    public ZonedDateTime getDate() {
        return gameDate;
    }

    public String getName() {
        return gameName;
    }

    public String getGamePlace() {return gamePlace;}

    public String getDescription() {
        return description;
    }
}
