package pl.slawek.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.slawek.model.Player;

@Controller
public class HomeController {
	@GetMapping
	public String home(Model model) {
		return "index";
	}
	
	@GetMapping("/add")
	public String addNewPlayer(Model model) {
		model.addAttribute("player", new Player());
		return "addnewplayer";
	}

}
