package pl.slawek.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.slawek.data.PlayerRepository;
import pl.slawek.model.Player;

@Component
public class TeamsCalculating {
	
	private PlayerRepository playerRepository;
	
	@Autowired
	public TeamsCalculating(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	
	private Player [] BlackTeam;
	private Player [] WhiteTeam;
	private Player [] AllPlayers = playerRepository. ;
	
	
	
}
