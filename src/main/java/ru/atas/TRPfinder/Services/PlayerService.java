package ru.atas.TRPfinder.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.atas.TRPfinder.Entities.Player;
import ru.atas.TRPfinder.Repositories.PlayerRepository;
import ru.atas.TRPfinder.Requests.PlayerRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers(){
        List<Player> list = new ArrayList<>();
        playerRepository.findAll().forEach(list::add);
        return list;
    }

    public Player getPlayerById(Long id){
        var a = playerRepository.findById(id);
        return a.get();
    }

    public void addPlayer(PlayerRequest player){
        playerRepository.addNewPlayer(player.name(), player.login());
    }

    public void updatePlayer(Player player){
        playerRepository.save(player);
    }

    public void deletePlayerById(Long id){
        playerRepository.deleteById(id);
    }

}
