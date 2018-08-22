package pl.slawek.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.slawek.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
