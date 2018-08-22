package pl.slawek.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import pl.slawek.data.PlayerRepository;
import pl.slawek.model.Player;

@Service
public class TeamsCalculating {
	
	private PlayerRepository playerRepository;
	Comparator <Player> komp = new Komparator();
	
	private List<Player> BlackTeam;
	private List<Player> WhiteTeam;
	private List<Player> AllPlayers = playerRepository.findAll();
	
	@Autowired
	public TeamsCalculating(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	
	
	public List<Player> getBlackTeam() {
		return BlackTeam;
	}


	public List<Player> getWhiteTeam() {
		return WhiteTeam;
	}

	

	/*
	for(int i=0 ; i < 1 ; i++)
	{
		AllPlayers.sort(komp);
		CalculateSquads(AllPlayers);
	}
	*/
	//Chose squads based on player skill
	private static void CalculateSquads(List <Player> allplayers)
	{
		int BlackTeamNumberOfPlayers = 0;
		int WhiteTeamNumberOfPlayers = 0;
		int BlackTeamPoints = 0;
		int WhiteTeamPoints = 0;
		
		
	}
	
	
	//Comparator with random when equal
	public class Komparator implements Comparator<Player>
	{
	 
	    @Override
	    public int compare(Player player1, Player player2) {
	    	if( player1.getSkillIndex() - player2.getSkillIndex() == 0 )
	    	{
	    		int pom = (int)(Math.random()*10);
	    		if(pom < 5) return 0;
	    		else return -1;
	    	}
	    		
	    	else return player1.getSkillIndex() - player2.getSkillIndex();
	    }
	}
	
}
