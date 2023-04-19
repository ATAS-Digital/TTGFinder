package ru.atas.TRPfinder.Entities;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="player")
public class Player {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String player_name;
    private String player_login;

    public Player(){

    }

    @Autowired
    public Player(Long id, String player_name, String player_login){
        this.id = id;
        this.player_name = player_name;
        this.player_login = player_login;
    }

    public Player(String player_name, String player_login){
        this.player_name = player_name;
        this.player_login = player_login;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return player_name;
    }

    public String getLogin() {
        return player_login;
    }
}
