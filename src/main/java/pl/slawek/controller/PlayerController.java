package pl.slawek.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.slawek.data.PlayerRepository;
import pl.slawek.model.Player;
import pl.slawek.service.TeamsCalculating;

@Controller
public class PlayerController {
	
	private PlayerRepository playerRepository;
	private TeamsCalculating teamscalculating;
	
	private List <Player> listOfPlayers;
	private Integer numberOfplayersInTeam;

	
	@Autowired
	public PlayerController(PlayerRepository playerRepository,TeamsCalculating teamscalculating) {
		this.playerRepository = playerRepository;
		this.teamscalculating = teamscalculating;
	}
	
	
	@GetMapping("/choose")
	public String choosePlayers(Model model) {
		List <Player> allPlayers = playerRepository.findAll();
		model.addAttribute("allPlayers",allPlayers);
		model.addAttribute("playersForGame",new ArrayList <Long>());
		return "chooseplayers";
	}
	
	@GetMapping("/show")
	public String showChosenPlayers(@RequestParam(value="PlayerID") List <Long> playersForGame,
			@RequestParam(value="numberOfPlayers") Integer numberOfplayersInTeam,
			Model model) {
		listOfPlayers = playerRepository.findAllByIdIn(playersForGame);
		this.numberOfplayersInTeam = numberOfplayersInTeam;
		model.addAttribute("listOfPlayers",listOfPlayers);
		return "showchosenplayers";
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
