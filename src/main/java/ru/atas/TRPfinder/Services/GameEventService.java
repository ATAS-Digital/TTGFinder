package ru.atas.TRPfinder.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atas.TRPfinder.Entities.GameEvent;
import ru.atas.TRPfinder.Records.GameEventRecord;
import ru.atas.TRPfinder.Repositories.GameEventRepository;

import java.util.List;
import java.util.stream.*;

@Service
public class GameEventService {
    private final GameEventRepository gameEventRepository;

    @Autowired
    public GameEventService(GameEventRepository gameEventRepository){
        this.gameEventRepository = gameEventRepository;
    }

    public List<GameEvent> getAllGames(){
        return StreamSupport
                .stream(gameEventRepository.findAll().spliterator(), false)
                .toList();
    }

    public GameEvent getGameById(Long id){
        return gameEventRepository.findById(id).get();
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
