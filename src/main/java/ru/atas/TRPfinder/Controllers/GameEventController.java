package ru.atas.TRPfinder.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.atas.TRPfinder.Entities.GameEvent;
import ru.atas.TRPfinder.Services.GameEventService;

import java.util.List;

@RestController
public class GameEventController {

    private final GameEventService gameEventService;

    @Autowired
    public GameEventController(GameEventService gameEventService){
        this.gameEventService = gameEventService;
    }

    @GetMapping("/games/list")
    public List<GameEvent> getAllGames(){
        return gameEventService.getAllGames();
    }
}
