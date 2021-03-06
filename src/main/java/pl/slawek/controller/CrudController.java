package pl.slawek.controller;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.slawek.data.PlayerRepository;
import pl.slawek.model.Player;
import pl.slawek.service.PlayerService;

@Controller
public class CrudController {

	private PlayerService playerService;
	private PlayerRepository playerRepository;
	
    @Autowired
    public CrudController(PlayerRepository playerRepository,PlayerService playerService ) {
        this.playerRepository = playerRepository;
        this.playerService = playerService;
    }
    
	@GetMapping("/manage")
	public String managePlayers(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("player", new Player());
		model.addAttribute("message", message);
		List <Player> allPlayers = playerRepository.findAll();
		model.addAttribute("allPlayers",allPlayers);
		return "manage";
	}
	
	@PostMapping("/save")
	public String saveNewPlayer(Model model, @Valid @ModelAttribute Player player, BindingResult result) {
			if(!result.hasErrors() && playerService.verifyPlayer(player))
			{
				playerRepository.save(player);
				return "redirect:/manage";
			}else
			{
	            List<ObjectError> errors = result.getAllErrors();
	            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
	    		List <Player> allPlayers = playerRepository.findAll();
	    		model.addAttribute("allPlayers",allPlayers);
	            return "manage";
			} 
        }
	
	@GetMapping("/find")
	public String showPlayer(@RequestParam String nickname,Model model,RedirectAttributes redirectAttributes) {
		Player player = playerRepository.findFirstByNickName(nickname);
		if(player!=null)
		{
			model.addAttribute("player",player);
			return "showplayer";
		}else
		{
			redirectAttributes.addFlashAttribute("message", "Error - there is no such player");
			return "redirect:/manage";
		}
		
	}
	
	@PostMapping("/edit")
	public String editPlayer(@RequestParam String nickname,Model model, RedirectAttributes redirectAttributes) {
		Player player = playerRepository.findFirstByNickName(nickname);
		if(player!=null)
		{
			model.addAttribute("player",player);
			return "editplayer";
		}else
		{
			redirectAttributes.addFlashAttribute("message", "Error - there is no such player");
			return "redirect:/manage";
		}
	}
	
	@PostMapping("/delete")
	public String deletePlayer(@RequestParam String nickname,Model model, RedirectAttributes redirectAttributes) {
		Player player = playerRepository.findFirstByNickName(nickname);
		if(player!=null)
		{
			model.addAttribute("player",player);
			playerRepository.delete(player);
			return "showplayer";
		}else
		{
			redirectAttributes.addFlashAttribute("message", "Error - there is no such player");
			return "redirect:/manage";
		}
	}
}
