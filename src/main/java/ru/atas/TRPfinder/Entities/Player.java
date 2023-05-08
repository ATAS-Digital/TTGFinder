package ru.atas.TRPfinder.Entities;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="player")
public class Player {
    @Id
    private Long id;

    private String player_name;

    public Player(){

    }

    public Player(Long id, String player_name){
        this.id = id;
        this.player_name = player_name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return player_name;
    }
}
