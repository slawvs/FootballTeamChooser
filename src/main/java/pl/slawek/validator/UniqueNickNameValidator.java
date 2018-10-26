package pl.slawek.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import pl.slawek.constraint.UniqueNickName;
import pl.slawek.data.PlayerRepository;
import pl.slawek.model.Player;


public class UniqueNickNameValidator implements ConstraintValidator<UniqueNickName, Player>   {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Override
    public void initialize(UniqueNickName constraintAnnotation) {
    }	
	
	@Override
	public boolean isValid(Player player,ConstraintValidatorContext context) {
		Player playerWithSameName = playerRepository.findFirstByNickName(player.getNickName());
		if(playerWithSameName==null) {
			return true;
		}else if (playerWithSameName!=null && playerWithSameName.getId()==player.getId()) {
			return true;
		}else {
			return false;
		}
	}
	

}
