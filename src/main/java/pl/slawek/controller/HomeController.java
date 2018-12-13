package pl.slawek.controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
		//test gameRecord
		/*
		ZonedDateTime date = ZonedDateTime.parse("2016-10-02T20:15:30-06:00",
                DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		GameRecord gamerecord1 = new GameRecord(date,2);
		Team team1 = new Team(1,5);
		Team team2 = new Team(2,5);
		Set <Team> teams = new HashSet<Team>();
		teams.add(team1);
		teams.add(team2);
		Set <Player> players1 = new HashSet<Player>();
		Set <Player> players2 = new HashSet<Player>();
		players1.add(playerRepository.getOne(1L));
		players1.add(playerRepository.getOne(2L));
		players2.add(playerRepository.getOne(3L));
		players2.add(playerRepository.getOne(4L));
		
		team1.setPlayers(players1);
		team2.setPlayers(players2);
		

		
		gamerecord1.setTeams(teams);
		team1.setGameRecord(gamerecord1);
		team2.setGameRecord(gamerecord1);
		gameRecordRepository.save(gamerecord1); 
		GameRecord gamerecord2 = gameRecordRepository.getOne(2L);
		System.out.println(teamRepository.getOne(1L));
		*/
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
