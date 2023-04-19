package ru.atas.TRPfinder.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.atas.TRPfinder.Entities.Player;
import ru.atas.TRPfinder.Requests.PlayerRequest;
import ru.atas.TRPfinder.Services.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    public PlayerController(){}//PlayerService playerService){
        //this.playerService = playerService;


    @GetMapping("/players/list")
    public List<Player> getTest(){
        System.out.println("get Players");
        return playerService.getPlayers();
    }

    @GetMapping("/players/{id}")
    public Player getById(@PathVariable Long id){
        return playerService.getPlayerById(id);
    }

    @PutMapping("/players/add")
    public void addPlayer(@Valid @RequestBody PlayerRequest player){
        playerService.addPlayer(player);
    }

    @PostMapping("/players/update/{id}")
    public void updatePlayer(@PathVariable Long id, @Valid @RequestBody PlayerRequest data){
        var player = new Player(id, data.name(), data.login());
        playerService.updatePlayer(player);
    }

    @DeleteMapping("/players/delete/{id}")
    public void deletePlayerById(@PathVariable Long id){
        playerService.deletePlayerById(id);
    }
}
