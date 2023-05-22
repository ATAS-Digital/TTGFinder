package ru.atas.TRPfinder.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.atas.TRPfinder.Entities.EventRegistration;
import ru.atas.TRPfinder.Entities.EventRegistrationId;
import ru.atas.TRPfinder.Entities.Role;
import ru.atas.TRPfinder.Records.EventRegistrationRecord;
import ru.atas.TRPfinder.Records.GameEventRecord;
import ru.atas.TRPfinder.Services.EventRegistrationService;

import java.util.List;

@RestController
public class EventRegistrationController {
    private final EventRegistrationService eventRegistrationService;

    @Autowired
    public EventRegistrationController(EventRegistrationService eventRegistrationService){
        this.eventRegistrationService = eventRegistrationService;
    }

    @GetMapping("/registrations/list")
    public List<EventRegistration> getAllGames(){
        return eventRegistrationService.getAll();
    }

    @GetMapping("/registrations/game/{gameId}")
    public List<EventRegistration> getAllGames(@PathVariable Long gameId){
        return eventRegistrationService.getRegistrationsOnGame(gameId);
    }

    @GetMapping("/registrations/roles")
    public List<Role> getAllRoles(){
        return eventRegistrationService.getAllRoles();
    }

    @PutMapping("/registrations/add")
    public void AddNewRegistration(@Valid @RequestBody EventRegistrationRecord body){
        var registration = new EventRegistration(body.playerId(), body.gameId(), body.role());
        eventRegistrationService.addNewRegistration(registration);
    }

//    @PostMapping("/registrations/update")
//    public List<Role> getAllRoles(){
//        return eventRegistrationService.getAllRoles();
//    }

    @DeleteMapping("/registrations/delete")
    public void DeleteRegistration(@Valid @RequestBody EventRegistrationRecord body){
        var id = new EventRegistrationId(body.playerId(), body.gameId());
        eventRegistrationService.deleteById(id);
    }
}
