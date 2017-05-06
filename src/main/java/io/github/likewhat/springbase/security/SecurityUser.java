package io.github.likewhat.springbase.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.github.likewhat.springbase.model.User;


public class SecurityUser implements UserDetails {

    private static final Logger log = LoggerFactory.getLogger(SecurityUser.class);

    private final User user;

    public SecurityUser(User user) {
        Assert.notNull(user, "User must not be null");
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        log.info("User authority=" + user.getAuthority());
        authorities.add(new SimpleGrantedAuthority(user.getAuthority()));
        return authorities;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isConfirmed();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isConfirmed();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isConfirmed();
    }

    @Override
    public boolean isEnabled() {
        return user.isConfirmed();
    }
}
