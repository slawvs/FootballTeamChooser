package pl.slawek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.slawek.data.PlayerRepository;
import pl.slawek.model.Player;

@Controller
public class PlayerController {
	
	private PlayerRepository playerRepository;
	
	@Autowired
	public PlayerController(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	
	@PostMapping("/save")
	public String saveTime(@ModelAttribute Player player) {
		playerRepository.save(player);
		return "redirect:/";
	}
	
	@GetMapping("/show")
	public String showSquads(Model model) {
		//here is code
		return "showsquads";
	}
}
