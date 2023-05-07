package ru.atas.TRPfinder.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

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

    public String getStringRole() {
        return role;
    }

    public EventRegistrationId getId(){
        return new EventRegistrationId(player_id, game_id);
    }
}
