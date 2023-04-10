package ru.atas.TRPfinder.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.atas.TRPfinder.Entities.EventRegistration;
import ru.atas.TRPfinder.Entities.Player;
import ru.atas.TRPfinder.Entities.Role;
import ru.atas.TRPfinder.Repositories.EventRegistrationRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class EventRegistrationService{

    private final EventRegistrationRepository eventRegistrationRepository;

    @Autowired
    public EventRegistrationService(EventRegistrationRepository eventRegistrationRepository){
        this.eventRegistrationRepository = eventRegistrationRepository;
    }

    public List<EventRegistration> getAll(){
        return StreamSupport
                .stream(eventRegistrationRepository.findAll().spliterator(), false)
                .toList();
    }

    public List<Role> getAllRoles(){
        return StreamSupport
                .stream(eventRegistrationRepository.findAll().spliterator(), false)
                .map(value -> value.getRole()).toList();
    }

}
