package io.github.likewhat.springbase.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.likewhat.springbase.model.Constants;
import io.github.likewhat.springbase.service.MessageService;
import io.github.likewhat.springbase.web.dto.Message;


@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    private final MessageService messageService;

    @Autowired
    public CustomAuthenticationSuccessHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        Object principal = authentication.getPrincipal();
        final String username = SecurityUtils.getCurrentUser(principal);

        log.info("User [ " + username + " ] login successfully.");

        Message message = Message.builder().withText(messageService.getMessage("user.logged.in")).build();

        request.getSession().setAttribute(Constants.SYSTEM_MESSAGE, message);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
