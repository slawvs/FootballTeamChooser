package pl.slawek;

import javax.validation.Validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class FootballTeamChooserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballTeamChooserApplication.class, args);
	}
	
	@Bean
	public Validator validator() {
	    return new LocalValidatorFactoryBean();
	}
}
