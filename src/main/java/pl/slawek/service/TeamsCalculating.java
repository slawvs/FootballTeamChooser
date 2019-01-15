package pl.slawek.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import pl.slawek.data.PlayerRepository;
import pl.slawek.model.Player;

@Component
@Scope(scopeName=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class TeamsCalculating {
	
	Comparator <Player> komp = new Komparator();
	
	private List<Player> BlackTeam;
	private List<Player> WhiteTeam;
	private List<Player> AllPlayers;
	private Integer numberOfplayersInTeam;
	
	@Autowired
	public TeamsCalculating() {
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
	
	public void setNumberOfplayersInTeam(Integer numberOfplayersInTeam) {
		this.numberOfplayersInTeam = numberOfplayersInTeam;
	}

	public void CalculateSquads() {
		BlackTeam = new ArrayList<Player>();
		WhiteTeam = new ArrayList<Player>();
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
		for(int i = 2*numberOfplayersInTeam-1 ; i>=0 ; i--)
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
					if(BlackTeamNumberOfPlayers > numberOfplayersInTeam-1 || WhiteTeamNumberOfPlayers > numberOfplayersInTeam-1)
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
