package pl.slawek.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.slawek.data.PlayerRepository;
import pl.slawek.model.Player;
import pl.slawek.service.TeamsCalculating;

@Controller
@Scope(scopeName=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CalculateTeamsController {
	
	private PlayerRepository playerRepository;
	private TeamsCalculating teamscalculating;
	
	private List <Player> listOfPlayers;
	private Integer numberOfplayersInTeam;

	
	@Autowired
	public CalculateTeamsController(PlayerRepository playerRepository,TeamsCalculating teamscalculating) {
		this.playerRepository = playerRepository;
		this.teamscalculating = teamscalculating;
	}
	
	
	@GetMapping("/choose")
	public String choosePlayers(Model model,@ModelAttribute("message") String message) {
		List <Player> allPlayers = playerRepository.findAll();
		model.addAttribute("allPlayers",allPlayers);
		model.addAttribute("playersForGame",new ArrayList <Long>());
		model.addAttribute("message", message);
		return "chooseplayers";
	}
	
	@GetMapping("/show")
	public String showChosenPlayers(@RequestParam(value="PlayerID" , required = false) List <Long> playersForGame,
			@RequestParam(value="numberOfPlayers") Integer numberOfplayersInTeam,
			Model model,
			RedirectAttributes redirectAttributes) {
		if( playersForGame!= null && playersForGame.size() >= 2*numberOfplayersInTeam ) 
		{
			listOfPlayers = playerRepository.findAllByIdIn(playersForGame);
			this.numberOfplayersInTeam = numberOfplayersInTeam;
			model.addAttribute("listOfPlayers",listOfPlayers);
			return "showchosenplayers";
		}else 
		{
			redirectAttributes.addFlashAttribute("message", "Error - you need to choose players for game at least twice as much as number of players in one team");
			return "redirect:/choose";
		}

	}
	
	@GetMapping("/calculate")
	public String calculateSquads(Model model) {
		model.addAttribute("listOfPlayers",listOfPlayers);
		teamscalculating.setAllPlayers(listOfPlayers);
		teamscalculating.setNumberOfplayersInTeam(numberOfplayersInTeam);
		teamscalculating.CalculateSquads();
		List <Player> blackTeam = teamscalculating.getBlackTeam();
		List <Player> whiteTeam = teamscalculating.getWhiteTeam();
		model.addAttribute("blackTeam",blackTeam);
		model.addAttribute("whiteTeam",whiteTeam);
		return "calculateteams";
	}
}
