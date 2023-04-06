package ru.atas.TRPfinder.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="player")
public class Player {
    @Id
    @GeneratedValue
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

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getName() {
        return player_name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public String getLogin() {
        return player_login;
    }

//    public void setLogin(String login) {
//        this.login = login;
//    }
}
