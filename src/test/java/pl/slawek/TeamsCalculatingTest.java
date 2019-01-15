package pl.slawek;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import pl.slawek.model.Player;
import pl.slawek.service.TeamsCalculating;

@ExtendWith(MockitoExtension.class)
class TeamsCalculatingTest {
	
	private List<Player> BlackTeam;
	private List<Player> WhiteTeam;
	//@Mock
	private List<Player> AllPlayers;
	//@Mock
	private Integer numberOfplayersInTeam;
	
	@Before
	void setUp() throws Exception {
		TeamsCalculating teamsCalculating = new TeamsCalculating();
		teamsCalculating.setAllPlayers(AllPlayers);
		teamsCalculating.setNumberOfplayersInTeam(numberOfplayersInTeam);
	}
	
	@Test
	void shouldTeamsHasExactNumberOfPlayers() {
		assertEquals(Double.valueOf(BlackTeam.size()),numberOfplayersInTeam);
		fail("BlackTeam doesn't have proper number of players");
	}

}
