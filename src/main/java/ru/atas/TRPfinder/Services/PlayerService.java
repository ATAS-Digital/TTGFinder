package ru.atas.TRPfinder.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.atas.TRPfinder.Entities.Player;
import ru.atas.TRPfinder.Repositories.PlayerRepository;

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
}
