package pl.slawek.controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.slawek.data.GameRecordRepository;
import pl.slawek.data.PlayerRepository;
import pl.slawek.data.TeamRepository;
import pl.slawek.model.GameRecord;
import pl.slawek.model.Player;
import pl.slawek.model.Team;
import pl.slawek.service.PlayerService;


@Controller
public class HomeController {
	
	private PlayerRepository playerRepository;
	private TeamRepository teamRepository;
	private GameRecordRepository gameRecordRepository;
	
	@Autowired
	public HomeController(PlayerRepository playerRepository, TeamRepository teamRepository,
			GameRecordRepository gameRecordRepository) {
		this.playerRepository = playerRepository;
		this.teamRepository = teamRepository;
		this.gameRecordRepository = gameRecordRepository;
	}

	@GetMapping("/")
	public String home(Model model) {
		return "index";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		return "about";
	}
	
	@GetMapping("/contact")
	public String contact(Model model) {
		return "contact";
	}
	
}
