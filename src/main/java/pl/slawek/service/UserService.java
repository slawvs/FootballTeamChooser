package pl.slawek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.slawek.data.UserRepository;
import pl.slawek.data.UserRoleRepository;
import pl.slawek.model.User;
import pl.slawek.model.UserRole;

@Service
public class UserService {
	
	private static final String DEFAULT_ROLE = "ROLE_USER";
	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
     
    @Autowired
    public void setRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }
    
    public void addWithDefaultRole(User user) {
        UserRole defaultRole = userRoleRepository.findByRole(DEFAULT_ROLE);
        user.getRoles().add(defaultRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    
}
