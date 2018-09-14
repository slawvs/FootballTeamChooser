package pl.slawek.component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import pl.slawek.data.PlayerRepository;
import pl.slawek.model.Player;

@Component
public class TeamsCalculating {
	
	//private PlayerRepository playerRepository;
	Comparator <Player> komp = new Komparator();
	
	private List<Player> BlackTeam;
	private List<Player> WhiteTeam;
	private List<Player> AllPlayers;
	
	@Autowired
	public TeamsCalculating(PlayerRepository playerRepository) {
		//this.playerRepository = playerRepository;
	}
	
	public void setAllPlayers(List<Player> AllPlayers) {
		this.AllPlayers = AllPlayers;
	}
	
	public List<Player> getBlackTeam() {
		return BlackTeam;
	}


	public List<Player> getWhiteTeam() {
		return WhiteTeam;
	}

	public void CalculateSquads() {
		BlackTeam = new ArrayList<Player>();
		WhiteTeam = new ArrayList<Player>();;
		//AllPlayers = playerRepository.findAll();
		AllPlayers.sort(komp);
		ChooseTeamForPlayer();

	}

	
	//Chose squads based on player skill
	private void ChooseTeamForPlayer()
	{
		int BlackTeamNumberOfPlayers = 0;
		int WhiteTeamNumberOfPlayers = 0;
		int BlackTeamPoints = 0;
		int WhiteTeamPoints = 0;
		for(int i = 9 ; i>=0 ; i--)
		{
			
				if(BlackTeamNumberOfPlayers-WhiteTeamNumberOfPlayers> 1)
				{
					WhiteTeam.add(AllPlayers.get(i));
					WhiteTeamNumberOfPlayers++;
					WhiteTeamPoints += AllPlayers.get(i).getSkillIndex();
				}else if(WhiteTeamNumberOfPlayers-BlackTeamNumberOfPlayers> 1)
				{
					BlackTeam.add(AllPlayers.get(i));
					BlackTeamNumberOfPlayers++;
					BlackTeamPoints += AllPlayers.get(i).getSkillIndex();
				}else
				{
					if(BlackTeamNumberOfPlayers > 4 || WhiteTeamNumberOfPlayers > 4)
					{
						if(BlackTeamNumberOfPlayers>WhiteTeamNumberOfPlayers)
						{
							WhiteTeam.add(AllPlayers.get(i));
							WhiteTeamNumberOfPlayers++;
							WhiteTeamPoints += AllPlayers.get(i).getSkillIndex();
						}else
						{
							BlackTeam.add(AllPlayers.get(i));
							BlackTeamNumberOfPlayers++;
							BlackTeamPoints += AllPlayers.get(i).getSkillIndex();
						}
					}else 
					{
						if(BlackTeamPoints >= WhiteTeamPoints)
						{
							WhiteTeam.add(AllPlayers.get(i));
							WhiteTeamNumberOfPlayers++;
							WhiteTeamPoints += AllPlayers.get(i).getSkillIndex();
						} else 
						{
							BlackTeam.add(AllPlayers.get(i));
							BlackTeamNumberOfPlayers++;
							BlackTeamPoints += AllPlayers.get(i).getSkillIndex();
						}
					}
					
				}

			
				
		}		
		
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
