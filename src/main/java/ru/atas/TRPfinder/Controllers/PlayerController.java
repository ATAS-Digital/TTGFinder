package ru.atas.TRPfinder.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.atas.TRPfinder.Entities.Player;
import ru.atas.TRPfinder.Services.PlayerService;

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
}
