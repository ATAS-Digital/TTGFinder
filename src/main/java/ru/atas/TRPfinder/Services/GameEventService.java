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
    private final EventRegistrationService eventRegistrationService;
    private final PlayerRepository playerRepository;

    @Autowired
    public GameEventService(GameEventRepository gameEventRepository,
                            EventRegistrationService eventRegistrationService,
                            PlayerRepository playerRepository){
        this.gameEventRepository = gameEventRepository;
        this.eventRegistrationService = eventRegistrationService;
        this.playerRepository = playerRepository;
    }

    public List<GameEvent> getAllGames(){
        return StreamSupport
                .stream(gameEventRepository.findAll().spliterator(), false)
                .toList();
    }

    public String getMasterName(Long gameId){
        var masterId = eventRegistrationService.getRegistrationsOnGame(gameId)
                .stream()
                .filter(x -> x.getRole() == Role.master)
                .toList().get(0).getPlayerId();
        return playerRepository.findById(masterId).get().getName();
    }

    public GameEvent getGameById(Long id){
        return gameEventRepository.findById(id).get();
    }

    public boolean addNewGame(GameEventRecord game) {
        if (getAllGames()
                .stream()
                .noneMatch(x -> game.name().equals(x.getName()))) {
            gameEventRepository.addNewGame(game.date(), game.name(), game.place(), game.description());
            return true;
        }
        return false;
    }

    public GameEvent getGameByName(String name){
        return gameEventRepository.getGameByName(name);
    }

    public void updateGame(GameEvent game){
        gameEventRepository.save(game);
    }

    public void deleteGameById(Long id){
        eventRegistrationService.deleteRegistrationsOnGame(id);
        gameEventRepository.deleteById(id);
    }

    public int getDoubledValue(int val){
        return val*2;
    }
}
