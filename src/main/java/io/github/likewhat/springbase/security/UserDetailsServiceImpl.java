package io.github.likewhat.springbase.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import io.github.likewhat.springbase.model.User;
import io.github.likewhat.springbase.service.UserService;


@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByUsername username=[ " + username + " ]");

        final User user = userService.findByEmail(username);

        if (user != null) {
            logger.info("user id=[ " + user.getId() + " ]");
            return new SecurityUser(user);
        }
        logger.info("username=[ " + username + " ] not found");
        throw new UsernameNotFoundException("Username [ " + username + " ] doesn't exist");
    }
}
