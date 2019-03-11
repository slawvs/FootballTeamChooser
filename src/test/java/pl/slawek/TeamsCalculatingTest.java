package pl.slawek;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import pl.slawek.model.Player;
import pl.slawek.service.TeamsCalculating;

@ExtendWith(MockitoExtension.class)
class TeamsCalculatingTest {
	
	TeamsCalculating teamsCalculating;
	private List<Player> AllPlayers;

	
	@BeforeEach
	void setUp() throws Exception {
		AllPlayers = new ArrayList<Player>();
		for(int i=0; i<10; i++)
		{
			Player e = new Player(i,"test1",3);
			AllPlayers.add(e);
		}
		teamsCalculating = new TeamsCalculating();
		teamsCalculating.setAllPlayers(AllPlayers);
		teamsCalculating.setNumberOfplayersInTeam(5);
		teamsCalculating.CalculateSquads();
	}
	
	@Test
	void shouldTeamsHasExactNumberOfPlayers() {
		assertEquals(Integer.valueOf(teamsCalculating.getBlackTeam().size()),teamsCalculating.getNumberOfplayersInTeam(),"BlackTeam doesn't have proper number of players");
		assertEquals(Integer.valueOf(teamsCalculating.getWhiteTeam().size()),teamsCalculating.getNumberOfplayersInTeam(),"WhiteTeam doesn't have proper number of players");
	}

}
