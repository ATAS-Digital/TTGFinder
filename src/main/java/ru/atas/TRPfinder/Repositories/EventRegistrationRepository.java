package ru.atas.TRPfinder.Repositories;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.atas.TRPfinder.Entities.EventRegistration;
import ru.atas.TRPfinder.Entities.EventRegistrationId;

import java.util.Collection;
import java.util.List;


public interface EventRegistrationRepository extends CrudRepository<EventRegistration, EventRegistrationId> {

}
