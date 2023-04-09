package ru.atas.TRPfinder.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atas.TRPfinder.Entities.GameEvent;
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
}
