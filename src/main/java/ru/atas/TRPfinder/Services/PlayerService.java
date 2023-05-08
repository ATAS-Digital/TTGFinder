package ru.atas.TRPfinder.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atas.TRPfinder.Entities.Player;
import ru.atas.TRPfinder.Repositories.PlayerRepository;
import ru.atas.TRPfinder.Records.PlayerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
        try{
            var a = playerRepository.findById(id);
            return a.get();
        }
        catch (NoSuchElementException ex){
            return null;
        }
    }

    public boolean addPlayer(PlayerRecord player){
        if (getPlayers()
                .stream()
                .noneMatch(x -> player.id().equals(x.getId()))){
            playerRepository.save(new Player(player.id(), player.name()));
            return true;
        }
        return false;
    }

    public void updatePlayer(Player player){
        playerRepository.save(player);
    }

    public void deletePlayerById(Long id){
        playerRepository.deleteById(id);
    }

}
