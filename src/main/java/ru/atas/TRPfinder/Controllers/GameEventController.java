package ru.atas.TRPfinder.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.atas.TRPfinder.Entities.GameEvent;
import ru.atas.TRPfinder.Entities.Player;
import ru.atas.TRPfinder.Records.GameEventRecord;
import ru.atas.TRPfinder.Records.PlayerRecord;
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

    @GetMapping("/games/{id}")
    public GameEvent getGameById(@PathVariable Long id){
        return gameEventService.getGameById(id);
    }

    @PutMapping("/games/add")
    public void addGameEvent(@Valid @RequestBody GameEventRecord game){
        gameEventService.addNewGame(game);
    }

    @PostMapping("/games/update/{id}")
    public void updateGameEvent(@PathVariable Long id, @Valid @RequestBody GameEventRecord data){
        var game = new GameEvent(id, data.date(), data.name(), data.description());
        gameEventService.updateGame(game);
    }

    @DeleteMapping("/games/delete/{id}")
    public void deleteGameById(@PathVariable Long id){
        gameEventService.deleteGameById(id);
    }
}
