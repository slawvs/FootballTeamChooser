package pl.slawek.data;


import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.slawek.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
	
	Player findFirstByNickName(String nickName);
	List <Player> findAllByIdIn(Collection <Long> playerList);
}
