package ru.atas.TRPfinder.Repositories;

import org.springframework.data.repository.CrudRepository;
import ru.atas.TRPfinder.Entities.Player;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}
