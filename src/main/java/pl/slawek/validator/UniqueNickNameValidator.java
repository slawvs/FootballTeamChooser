package pl.slawek.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import pl.slawek.constraint.UniqueNickName;
import pl.slawek.data.PlayerRepository;

public class UniqueNickNameValidator implements ConstraintValidator<UniqueNickName, String>   {
	
	@Autowired
	private PlayerRepository playerRepository;
	
    @Override
    public void initialize(UniqueNickName constraintAnnotation) {
    }	
	
	@Override
	public boolean isValid(String nickName, ConstraintValidatorContext context) {
		return nickName != null && playerRepository.findFirstByNickName(nickName)==null;
	}
	

}
