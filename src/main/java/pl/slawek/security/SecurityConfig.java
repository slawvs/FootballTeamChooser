package pl.slawek.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public UserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers(
					"/",
					"/about",
					"/contact",
					"/webjars/**",
					"/css/**",
					"/register").permitAll()
			.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
		.and()
			.logout()
			.permitAll();
	}
}
