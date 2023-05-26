package ru.atas.TRPfinder.Entities;

import jakarta.persistence.*;


@Entity
@Table(name="event_registrations")
@IdClass(EventRegistrationId.class)
public class EventRegistration {
    @Id
    @Column(name = "player_id")
    private Long playerId;
    @Id
    @Column(name = "game_id")
    private Long gameId;
    private String role;

    public EventRegistration(){
    }

    public EventRegistration(Long player_id, Long game_id, String role){
        this.playerId = player_id;
        this.gameId = game_id;
        System.out.println(Role.valueOf(role));
        this.role = role;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Long getGameId() {
        return gameId;
    }

    public Role getRole() {
        return Role.valueOf(role);
    }

    public String getStringRole() {
        return role;
    }

    public EventRegistrationId getId(){
        return new EventRegistrationId(playerId, gameId);
    }
}
