package blackmhofu.com.auth.utils;


import blackmhofu.com.auth.config.UserInfoUserDetails;
import blackmhofu.com.users.model.User;
import blackmhofu.com.users.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class CurrentLoggedInUser {

    @Autowired
    private UserServiceImpl userService;
    public User getCurrentLoginUser(){
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        UserInfoUserDetails userPrincipal = (UserInfoUserDetails) authentication.getPrincipal();
        return  userService.findByEmail(userPrincipal.getUsername());

    }

}
