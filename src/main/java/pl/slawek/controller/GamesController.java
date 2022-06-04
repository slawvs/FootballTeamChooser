package pl.slawek.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;

import pl.slawek.data.GameRecordRepository;
import pl.slawek.model.GameRecord;
import pl.slawek.model.Team;
import pl.slawek.service.TeamsCalculating;
import pl.slawek.session.SessionUserInformations;

@Controller
@SessionAttributes("gameRecord")
@Scope(scopeName=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class GamesController {
	
	private TeamsCalculating teamscalculating;
	private GameRecordRepository gameRecordRepository;
	private SessionUserInformations sessionUserInformations;
	
	
	@Autowired
	public GamesController(TeamsCalculating teamscalculating, GameRecordRepository gameRecordRepository, SessionUserInformations sessionUserInformations) {
		this.teamscalculating = teamscalculating;
		this.gameRecordRepository = gameRecordRepository;
		this.sessionUserInformations = sessionUserInformations;
	}

	@PostMapping("/newGame")
	public String newGameRecord(Model model) {
		GameRecord gameRecord = new GameRecord();
		gameRecord.setNumberOfTeams(2);
		gameRecord.setDate(LocalDateTime.now());
		Team blackTeam = new Team();
		Team whiteTeam = new Team();
		blackTeam.setIndex(1);
		whiteTeam.setIndex(2);
		gameRecord.addTeam(blackTeam);
		gameRecord.addTeam(whiteTeam);
		model.addAttribute("gameRecord", gameRecord);
		return "newGame";
        }
	
	@PostMapping("/saveGame")
	public String saveGameRecord(Model model, @ModelAttribute GameRecord gameRecord) {
		gameRecord.setGameCreator(sessionUserInformations.getCurrentlyLogedUser());
		gameRecordRepository.save(gameRecord);
		model.addAttribute("gameRecord", gameRecord);
		return "savedRecord";
        }
	
	@GetMapping("/showHistory")
	public String managePlayers(Model model) {
		List <GameRecord> allGames = gameRecordRepository.findAll();
		model.addAttribute("allGames", allGames);
		return "history";
	}
}
