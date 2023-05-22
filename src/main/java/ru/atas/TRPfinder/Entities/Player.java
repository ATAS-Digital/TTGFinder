package ru.atas.TRPfinder.Entities;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="player")
public class Player {
    @Id
    private Long id;
    @Column(name = "player_name")
    private String playerName;

    public Player(){

    }

    public Player(Long id, String playerName){
        this.id = id;
        this.playerName = playerName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return playerName;
    }
}
