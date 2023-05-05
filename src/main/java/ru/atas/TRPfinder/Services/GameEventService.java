package ru.atas.TRPfinder.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atas.TRPfinder.Entities.GameEvent;
import ru.atas.TRPfinder.Entities.Role;
import ru.atas.TRPfinder.Records.GameEventRecord;
import ru.atas.TRPfinder.Repositories.EventRegistrationRepository;
import ru.atas.TRPfinder.Repositories.GameEventRepository;
import ru.atas.TRPfinder.Repositories.PlayerRepository;

import java.util.List;
import java.util.stream.*;

@Service
public class GameEventService {
    private final GameEventRepository gameEventRepository;
    private final EventRegistrationRepository eventRegistrationRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public GameEventService(GameEventRepository gameEventRepository,
                            EventRegistrationRepository eventRegistrationRepository,
                            PlayerRepository playerRepository){
        this.gameEventRepository = gameEventRepository;
        this.eventRegistrationRepository = eventRegistrationRepository;
        this.playerRepository = playerRepository;
    }

    public List<GameEvent> getAllGames(){
        return StreamSupport
                .stream(gameEventRepository.findAll().spliterator(), false)
                .toList();
    }

    public String getMasterName(Long gameId){
        var masterId = eventRegistrationRepository.GetGameRegistrations(gameId)
                .stream()
                .filter(x -> x.getRole() == Role.master)
                .toList().get(0).getPlayerId();
        return playerRepository.findById(masterId).get().getName();
    }

    public GameEvent getGameById(Long id){
        return gameEventRepository.findById(id).get();
    }

    public GameEvent getGameByName(String name){
        return gameEventRepository.getGameByName(name);
    }
    public void addNewGame(GameEventRecord game){
        gameEventRepository.addNewGame(game.date(), game.name(), game.place(), game.description());
    }

    public void updateGame(GameEvent game){
        gameEventRepository.save(game);
    }

    public void deleteGameById(Long id){
        gameEventRepository.deleteById(id);
    }
}
