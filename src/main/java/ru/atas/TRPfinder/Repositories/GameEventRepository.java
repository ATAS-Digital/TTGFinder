package ru.atas.TRPfinder.Repositories;

import org.springframework.data.repository.CrudRepository;
import ru.atas.TRPfinder.Entities.GameEvent;

public interface GameEventRepository extends CrudRepository<GameEvent, Long> {
}
