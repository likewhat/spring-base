package io.github.likewhat.springbase.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.Collections;

import javax.validation.Valid;

import io.github.likewhat.springbase.annotation.LoginUser;
import io.github.likewhat.springbase.exception.EmailAlreadyInUseException;
import io.github.likewhat.springbase.model.Constants;
import io.github.likewhat.springbase.model.User;
import io.github.likewhat.springbase.security.SecurityUser;
import io.github.likewhat.springbase.service.UserService;
import io.github.likewhat.springbase.web.dto.ListableMessage;
import io.github.likewhat.springbase.web.dto.Message;
import io.github.likewhat.springbase.web.dto.MessageType;
import io.github.likewhat.springbase.web.form.ChangeAccountTypeForm;
import io.github.likewhat.springbase.web.form.ChangeEmailForm;
import io.github.likewhat.springbase.web.form.InviteUserForm;
import io.github.likewhat.springbase.web.form.RegistrationForm;
import io.github.likewhat.springbase.web.util.UrlUtil;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;


    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Show admin homepage.
     */
    @GetMapping("/")
    public String showIndex() {
        return "admin/index";
    }


    @GetMapping("/new-user")
    public String showNewUser(Model model) {
        model.addAttribute(Constants.FORM, new RegistrationForm());
        return "admin/new_user";
    }

    /**
     * Save new user.
     *
     * @param form          new user form
     * @param bindingResult validating result
     * @param appUser       current login user
     */
    @PostMapping("/new-user")
    public String saveNewUser(@Valid @ModelAttribute(Constants.FORM) RegistrationForm form,
                              BindingResult bindingResult,
                              @LoginUser SecurityUser appUser) {
        if (bindingResult.hasErrors()) {
            return "admin/new_user";
        }
        try {
            userService.createUser(form, appUser.getUser().getId());
            return UrlUtil.redirect("/admin/users");
        } catch (EmailAlreadyInUseException e) {
            bindingResult.rejectValue(User.EMAIL, "error.email.exist", Constants.EMAIL_EXIST);
        }
        return "admin/new_user";
    }

    @GetMapping("/invite-user")
    public String showInviteUser(Model model) {
        model.addAttribute(Constants.FORM, new InviteUserForm());
        return "admin/invite_user";
    }

    @PostMapping("/invite-user")
    public String inviteUser(@Valid @ModelAttribute(Constants.FORM) InviteUserForm form,
                             @LoginUser SecurityUser user,
                             RedirectAttributesModelMap model) {
        userService.inviteUser(form, user.getUser());

        Message message = Message.builder().withText("Successfully sent invitation email to ").build();
        model.addFlashAttribute(Constants.MESSAGE, message);

        return UrlUtil.redirect("/admin/invite-user");
    }

    /**
     * Show all registered users.
     */
    @GetMapping("/users")
    public String showRegisteredUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "admin/users";
    }

    /**
     * Show user information
     *
     * @param userId id of user to be shown
     * @param model  model to save response
     */
    @GetMapping(value = {
            "/user/{id}", "/user/{id}/info"
    })
    public String showUserInfo(@PathVariable("id") Long userId,
                               Model model) {
        model.addAttribute(Constants.USER, userService.getOrThrow(userId));
        return "admin/user_info";
    }

    /**
     * Show change user email form.
     *
     * @param userId The id of user to change
     * @param model  The model used to store result
     */
    @GetMapping("/user/{id}/change-email")
    public String showUserChangeEmail(@PathVariable("id") Long userId,
                                      Model model) {
        model.addAttribute(Constants.USER, userService.getOrThrow(userId));
        model.addAttribute(Constants.FORM, new ChangeEmailForm());
        return "admin/change_email";
    }

    /**
     * Change user email.
     *
     * @param form The form submitted from UI
     * @param user The current login user
     */
    @PostMapping("/user/{id}/change-email")
    public String changeEmail(@Valid @ModelAttribute(Constants.FORM) ChangeEmailForm form,
                              @LoginUser SecurityUser user) {
        userService.changeEmailFor(form, user.getUser());
        return UrlUtil.redirect("/admin/user/" + user.getUser().getId());
    }


    @GetMapping("/user/{id}/change-account-type")
    public String showChangeAccountType(@PathVariable("id") Long userId,
                                        Model model) {
        model.addAttribute(Constants.USER, userService.getOrThrow(userId));
        model.addAttribute(Constants.FORM, new ChangeAccountTypeForm());
        return "admin/change_account_type";
    }


    @PostMapping("/user/{id}/change-account-type")
    public String changeAccountType(@PathVariable("id") Long id,
                                    @Valid @ModelAttribute(Constants.FORM) ChangeAccountTypeForm form,
                                    @LoginUser SecurityUser user,
                                    Model model) {

        User changedUser = userService.changeAccountTypeFor(id, form, user.getUser());

        String successMessage = String.format(
                "Role for user %s successfully changed to %s.",
                changedUser.getFullName(),
                changedUser.getAccountType());

        ListableMessage message = new ListableMessage().withType(MessageType.SUCCESS)
                .withHeader("Success!")
                .withMessages(Collections.singletonList(successMessage));

        model.addAttribute(Constants.MESSAGE_LIST, message);

        return UrlUtil.redirect("/admin/user/" + changedUser.getId() + "/change-account-type");
    }


    @GetMapping("/user/{id}/delete")
    public String showDeleteUser(@PathVariable("id") Long userId,
                                 Model model) {
        model.addAttribute(Constants.USER, userService.getOrThrow(userId));
        return "admin/delete_user";
    }

    @PostMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable("id") Long userId,
                             @LoginUser SecurityUser appUser,
                             RedirectAttributesModelMap model) {

        User deletedUser = userService.delete(userId, appUser.getUser());

        String text = String.format("Successfully deleted user %s.", deletedUser.getFullName());
        Message message = Message.builder().withText(text).build();

        model.addFlashAttribute(Constants.MESSAGE, message);
        return UrlUtil.redirect("/admin/users");
    }

}
