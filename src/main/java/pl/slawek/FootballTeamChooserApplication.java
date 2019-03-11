package pl.slawek;


import java.util.Arrays;

import javax.validation.Validator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;


@SpringBootApplication
public class FootballTeamChooserApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(FootballTeamChooserApplication.class, args);
		
	}
	
	@Bean
	public Validator validator() {
	    return new LocalValidatorFactoryBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
	    return new SpringSecurityDialect();
	}
	
	//to check all beans from Spring Context
	/*
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }
	*/
}
