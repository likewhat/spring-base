package io.github.likewhat.springbase.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.Collections;

import javax.validation.Valid;

import io.github.likewhat.springbase.annotation.LoginUser;
import io.github.likewhat.springbase.exception.EmailAlreadyInUseException;
import io.github.likewhat.springbase.model.Constants;
import io.github.likewhat.springbase.model.User;
import io.github.likewhat.springbase.security.SecurityUser;
import io.github.likewhat.springbase.service.MessageService;
import io.github.likewhat.springbase.service.UserService;
import io.github.likewhat.springbase.web.dto.ListableMessage;
import io.github.likewhat.springbase.web.dto.Message;
import io.github.likewhat.springbase.web.dto.MessageType;
import io.github.likewhat.springbase.web.form.ChangeEmailForm;
import io.github.likewhat.springbase.web.form.ChangePasswordForm;
import io.github.likewhat.springbase.web.form.RegistrationForm;
import io.github.likewhat.springbase.web.form.ResetPasswordForm;
import io.github.likewhat.springbase.web.util.UrlUtil;


@Controller
@RequestMapping("/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final UserService userService;

    private final MessageService messageService;

    @Autowired
    public AccountController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }


    @GetMapping(value = "/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
                                Model model) {

        if (error != null) {
            ListableMessage message = new ListableMessage()
                    .withType(MessageType.ERROR)
                    .withHeader("Something went wrong.")
                    .withMessages(Collections.singletonList("Invalid email or password."));

            model.addAttribute(Constants.MESSAGE_LIST, message);
        }
        return "account/login";
    }

    @GetMapping(value = {
            "/manage", "/manage/info"
    })
    public String showManagePage(Model model, @LoginUser SecurityUser user) {
        logger.info("Username = " + user.getUsername());
        model.addAttribute(Constants.USER, user.getUser());
        model.addAttribute(Constants.ACTIVE, "INFO");
        return "account/manage";
    }

    @GetMapping("/manage/change-email")
    public String showChangeEmail(Model model) {
        model.addAttribute(Constants.ACTIVE, "CHANGE_EMAIL");
        model.addAttribute(Constants.FORM, new ChangeEmailForm());
        return "account/change_email";
    }

    @PostMapping("/manage/change-email")
    public String changeEmail(@Valid @ModelAttribute(Constants.FORM) ChangeEmailForm form,
                              @LoginUser SecurityUser user) {
        userService.changeEmailFor(form, user.getUser());
        return UrlUtil.redirect("/manage");
    }

    @GetMapping("/manage/change-password")
    public String showChangePassword(Model model) {
        model.addAttribute(Constants.ACTIVE, "CHANGE_PASSWORD");
        model.addAttribute(Constants.FORM, new ChangePasswordForm());
        return "account/change_password";
    }

    @PostMapping("/manage/change-password")
    public String changePassword(@Valid @ModelAttribute(Constants.FORM) ChangePasswordForm form,
                                 @LoginUser SecurityUser user) {
        userService.changePasswordFor(form, user.getUser());
        return UrlUtil.redirect("/manage");
    }

    @GetMapping(value = "/register")
    public String showRegisterForm(Model model) {
        model.addAttribute(Constants.FORM, new RegistrationForm());
        return "account/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute(Constants.FORM) RegistrationForm form,
                           BindingResult bindingResult,
                           RedirectAttributesModelMap model) {
        if (bindingResult.hasErrors()) {
            return "account/register";
        }

        try {
            final User user = userService.register(form);
            final String text = messageService.getMessage("user.confirmation.link.sent", new Object[]{user.getEmail()});

            Message message = Message.builder().withText(text).withType(MessageType.INFO).build();

            model.addFlashAttribute(Constants.MESSAGE, message);

            return UrlUtil.redirect("/");
        } catch (EmailAlreadyInUseException e) {
            bindingResult.rejectValue(User.EMAIL, "email.already.exist", Constants.EMAIL_EXIST);
        }

        return "account/register";
    }

    @GetMapping("/reset-password")
    public String showResetPassword(Model model) {
        model.addAttribute(Constants.FORM, new ResetPasswordForm());
        return "account/reset_password";
    }


    @PostMapping("/reset-password")
    public String resetPassword(@Valid @ModelAttribute(Constants.FORM) ResetPasswordForm form,
                                RedirectAttributesModelMap modelMap) {
        userService.resetPassword(form);

        Message message = Message.builder().withType(MessageType.WARNING)
                .withText(String.format("A password reset link has been sent to %s.", form.getEmail()))
                .build();

        modelMap.addFlashAttribute(Constants.MESSAGE, message);

        return UrlUtil.redirect("/account/login");
    }

}
