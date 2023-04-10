package ru.atas.TRPfinder.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.atas.TRPfinder.Entities.EventRegistration;
import ru.atas.TRPfinder.Entities.Role;
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

    @GetMapping("/registrations/roles")
    public List<Role> getAllRoles(){
        return eventRegistrationService.getAllRoles();
    }
}
