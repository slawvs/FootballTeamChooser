package pl.slawek.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.slawek.component.TeamsCalculating;
import pl.slawek.data.PlayerRepository;
import pl.slawek.model.Player;

@Controller
public class PlayerController {
	
	private PlayerRepository playerRepository;
	private TeamsCalculating teamscalculating;
	private List <Player> playersForGame;
	private Player gracz;
	// private boolean DoTeamsWereCalculate = false;
	
	@Autowired
	public PlayerController(PlayerRepository playerRepository,TeamsCalculating teamscalculating) {
		this.playerRepository = playerRepository;
		this.teamscalculating = teamscalculating;
	}
	
	@PostMapping("/save")
	public String saveTime(@ModelAttribute Player player) {
		playerRepository.save(player);
		return "redirect:/";
	}
	
	@GetMapping("/choose")
	public String showSquads(Model model) {
		List <Player> allPlayers = playerRepository.findAll();
		model.addAttribute("allPlayers",allPlayers);
		model.addAttribute("playersForGame",new Player());
		return "chooseplayers";
	}
	
	@GetMapping("/calculate")
	public String calculateSquads(@ModelAttribute Player gracz, Model model) {
		model.addAttribute("playersForGame",gracz);
		System.out.println(gracz.getNickName());
//dodac ZAWARTOSC
		return "calculateteams";
	}
	
	
	/*
	@GetMapping("/show")
	public String showSquads(Model model) {
		if(DoTeamsWereCalculate==false)
		{
			teamscalculating.CalculateSquads();
			DoTeamsWereCalculate = true;
		}
		List <Player> blackTeam = teamscalculating.getBlackTeam();
		List <Player> whiteTeam = teamscalculating.getWhiteTeam();
		model.addAttribute("blackTeam",blackTeam);
		model.addAttribute("whiteTeam",whiteTeam);
		return "showsquads";
	}
	*/
}
