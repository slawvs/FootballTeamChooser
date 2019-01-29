package pl.slawek.controller;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;

import pl.slawek.data.GameRecordRepository;
import pl.slawek.model.GameRecord;
import pl.slawek.model.Player;
import pl.slawek.model.Team;
import pl.slawek.service.TeamsCalculating;

@Controller
@SessionAttributes("gameRecord")
@Scope(scopeName=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class HistoryOfGamesController {
	private TeamsCalculating teamscalculating;
	private GameRecordRepository gameRecordRepository;
	
	@Autowired
	public HistoryOfGamesController(TeamsCalculating teamscalculating, GameRecordRepository gameRecordRepository) {
		this.teamscalculating = teamscalculating;
		this.gameRecordRepository = gameRecordRepository;
	}

	@PostMapping("/newGame")
	public String newGameRecord(Model model) {
		GameRecord gameRecord = new GameRecord();
		gameRecord.setNumberOfTeams(2);
		gameRecord.setDate(LocalDateTime.now());
		Team blackTeam = new Team();
		Team whiteTeam = new Team();
		blackTeam.setPlayers(teamscalculating.getBlackTeam());
		whiteTeam.setPlayers(teamscalculating.getWhiteTeam());
		blackTeam.setIndex(1);
		whiteTeam.setIndex(2);
		gameRecord.addTeam(blackTeam);
		gameRecord.addTeam(whiteTeam);
		model.addAttribute("gameRecord", gameRecord);
		return "newGame";
        }
	
	@PostMapping("/saveGame")
	public String saveGameRecord(Model model, @ModelAttribute GameRecord gameRecord) {
		gameRecordRepository.save(gameRecord);
		model.addAttribute("gameRecord", gameRecord);
		return "savedRecord";
        }
	
	@GetMapping("/showHistory")
	public String managePlayers() {
		//Under Construction
		return "history";
	}
}
