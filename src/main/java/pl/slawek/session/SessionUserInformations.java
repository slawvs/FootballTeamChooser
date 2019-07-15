package pl.slawek.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.slawek.data.UserRepository;
import pl.slawek.model.User;

@Component
public class SessionUserInformations {

    @Autowired
    UserRepository userRepository;

    public String getCurrentlyLoggedUser()
    {
        String username = "";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object userObject = auth.getPrincipal();
        if (userObject instanceof UserDetails)
        {
            username = ((UserDetails)userObject).getUsername();
        }
        return username;
    }

    public User getCurrentlyLogedUser()
    {
        User user = userRepository.findByEmail(this.getCurrentlyLoggedUser());
        return user;
    }
}
