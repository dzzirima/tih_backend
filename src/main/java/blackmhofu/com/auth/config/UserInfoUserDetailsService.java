package blackmhofu.com.auth.config;




import blackmhofu.com.users.model.User;
import blackmhofu.com.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = repository.findUserByEmail(username);
        return userInfo.map((User userInfo1) -> new UserInfoUserDetails(userInfo1))
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}