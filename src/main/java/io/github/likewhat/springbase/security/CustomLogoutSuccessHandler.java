package io.github.likewhat.springbase.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.likewhat.springbase.model.Constants;
import io.github.likewhat.springbase.service.MessageService;
import io.github.likewhat.springbase.web.dto.Message;
import io.github.likewhat.springbase.web.dto.MessageType;

/**
 * Logout success handler used to inject logout success message.
 */
@Component("customLogoutSuccessHandler")
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);

    private final MessageService messageService;

    @Autowired
    public CustomLogoutSuccessHandler(MessageService messageService) {
        this.messageService = messageService;
    }


    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        Object principal = authentication.getPrincipal();
        final String username = SecurityUtils.getCurrentUser(principal);

        log.info("User [ " + username + " ] logout successfully.");

        Message message = Message.builder()
                .withType(MessageType.INFO)
                .withText(messageService.getMessage("user.logged.out"))
                .build();

        request.getSession().setAttribute(Constants.SYSTEM_MESSAGE, message);

        super.onLogoutSuccess(request, response, authentication);
    }
}
