package pl.slawek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.slawek.data.PlayerRepository;
import pl.slawek.model.Player;

@Controller
public class CrudController {

	private PlayerRepository playerRepository;
	
    @Autowired
    public CrudController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    
	@GetMapping("/manage")
	public String managePlayers(Model model) {
		model.addAttribute("player", new Player());
		return "manage";
	}
	
	@PostMapping("/save")
	public String saveNewPlayer(@ModelAttribute Player player) {
		playerRepository.save(player);
		return "redirect:/manage";
	}
	
	@GetMapping("/find")
	public String showPlayer(@RequestParam String nickname,Model model) {
		model.addAttribute("player",playerRepository.findFirstByNickName(nickname));
		return "showplayer";
	}
	
	@PostMapping("/edit")
	public String editPlayer(@RequestParam String nickname,Model model) {
		model.addAttribute("player",playerRepository.findFirstByNickName(nickname));
		return "editplayer";
	}
	
	@PostMapping("/delete")
	public String deletePlayer(@RequestParam String nickname,Model model) {
		Player player = playerRepository.findFirstByNickName(nickname);
		model.addAttribute("player",player);
		playerRepository.delete(player);
		return "showplayer";
	}
}
