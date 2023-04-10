package ru.atas.TRPfinder.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="event_registrations")
@IdClass(EventRegistrationId.class)
public class EventRegistration {
    @Id
    private Long player_id;
    @Id
    private Long game_id;
    private String role;

    public EventRegistration(){
    }

    @Autowired
    public EventRegistration(Long player_id, Long game_id, String role){
        this.player_id = player_id;
        this.game_id = game_id;
        System.out.println(Role.valueOf(role));
        this.role = role;
    }

    public Long getPlayerId() {
        return player_id;
    }

    public Long getGameId() {
        return game_id;
    }

    public Role getRole() {
        return Role.valueOf(role);
    }
}
