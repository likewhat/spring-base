package io.github.likewhat.springbase.web.advice;


import io.github.likewhat.springbase.annotation.LoginUser;
import io.github.likewhat.springbase.security.SecurityUser;
import io.github.likewhat.springbase.model.Constants;
import io.github.likewhat.springbase.model.User;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice
public class CurrentUserAdvice {


    @ModelAttribute(Constants.CURRENT_USER)
    public User saveCurrentUser(@LoginUser SecurityUser user) {
        return user == null ? null : user.getUser();
    }
}
