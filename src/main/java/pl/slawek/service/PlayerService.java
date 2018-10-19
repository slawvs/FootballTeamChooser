package pl.slawek.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.slawek.data.PlayerRepository;
import pl.slawek.model.Player;

@Service
public class PlayerService {
	private Validator validator;
	private PlayerRepository playerRepository;
	
	@Autowired
	public PlayerService(Validator validator) {
		this.validator = validator;
	}
	public boolean verifyPlayer(Player player) {
        Set<ConstraintViolation<Player>> errors = validator.validate(player);
        if(!errors.isEmpty()) {
            errors.forEach(err -> System.err.println(err.getMessage()));
            return false;
        } else {
        	return true;
        }
	}
	
	public Player findPlayerInDatabase(String nickName) {
		Player player = playerRepository.findFirstByNickName(nickName);
		return player;
	}

}
