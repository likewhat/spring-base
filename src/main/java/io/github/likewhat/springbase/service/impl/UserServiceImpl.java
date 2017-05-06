package io.github.likewhat.springbase.service.impl;

import io.github.likewhat.springbase.exception.AuthenticationException;
import io.github.likewhat.springbase.exception.EmailAlreadyInUseException;
import io.github.likewhat.springbase.exception.UserNotExistException;
import io.github.likewhat.springbase.model.AccountType;
import io.github.likewhat.springbase.model.Constants;
import io.github.likewhat.springbase.model.Email;
import io.github.likewhat.springbase.model.User;
import io.github.likewhat.springbase.repository.UserRepository;
import io.github.likewhat.springbase.security.SecurityUtils;
import io.github.likewhat.springbase.service.EmailService;
import io.github.likewhat.springbase.service.MessageService;
import io.github.likewhat.springbase.service.UserService;
import io.github.likewhat.springbase.web.form.ChangeAccountTypeForm;
import io.github.likewhat.springbase.web.form.ChangeEmailForm;
import io.github.likewhat.springbase.web.form.ChangePasswordForm;
import io.github.likewhat.springbase.web.form.InviteUserForm;
import io.github.likewhat.springbase.web.form.RegistrationForm;
import io.github.likewhat.springbase.web.form.ResetPasswordForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    private final UserRepository userRepository;

    private final EmailService emailService;

    private final MessageService messageService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           EmailService emailService,
                           MessageService messageService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.messageService = messageService;
    }

    @Override
    CrudRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    public User findByEmail(String email) {
        Assert.hasText(email, "Email must not be empty");
        return userRepository.findByEmailAndIsDeletedFalse(email);
    }

    @Override
    public User getOrThrow(Long id) {
        User user = findOne(id);
        if (user == null) {
            throw new UserNotExistException(id);
        }
        return user;
    }

    @Override
    public User register(final RegistrationForm form) {
        final User user = createUser(form, Constants.ANONYMOUS_USER);

        // TODO
        Email email = new Email();
        email.setReceiverId(user.getId());
        email.setSendTo(user.getEmail());

        emailService.sendAsync(email);

        // send inform email
        return user;
    }

    @Override
    public User createUser(RegistrationForm form, Long creator) {
        Assert.notNull(form, "RegistrationForm must not be null!");

        final User userUseEmail = findByEmail(form.getEmail());
        if (userUseEmail != null) {
            throw new EmailAlreadyInUseException(form.getEmail());
        }

        User user = new User();
        user.setEmail(form.getEmail());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setAccountType(AccountType.fromString(form.getAccountType()).name());
        user.setConfirmed(false);
        user.setPassword(SecurityUtils.encodePassword(form.getPassword()));

        // No anyone create this user
        return create(user, creator);
    }

    @Override
    public User delete(Long id, User deleter) {
        User user = getOrThrow(id);
        // if is current user
        if (user.getEmail().equals(deleter.getEmail())) {
            throw new AuthenticationException(messageService.getMessage("error.delete.own.account"));
        }
        // else do delete the user
        delete(id, deleter.getId());

        return user;
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public void changeEmailFor(ChangeEmailForm form, User user) {
        Assert.notNull(form, "ChangeEmailForm must not be null!");
        User userUseEmail = findByEmail(form.getNewEmail());
        if (userUseEmail != null) {
            throw new EmailAlreadyInUseException(form.getNewEmail());
        }
        String password = SecurityUtils.encodePassword(form.getPassword());
        if (!user.getPassword().equals(password)) {
            throw new AuthenticationException("Invalid password");
        }
        user.setEmail(form.getNewEmail());
        //TODO send a confirm email

        update(user, user.getId());
    }

    @Override
    public void changePasswordFor(ChangePasswordForm form, User user) {
        String password = SecurityUtils.encodePassword(form.getPassword());
        if (!user.getPassword().equals(password)) {
            throw new AuthenticationException("Invalid password");
        }
        user.setPassword(password);
        // TODO send email
        update(user, user.getId());
    }

    @Override
    public void inviteUser(InviteUserForm form, User user) {
        // Send invation email
        Email email = new Email();
        email.setSendTo(form.getEmail());
        email.setReceiverId(Constants.ANONYMOUS_USER);
        email.setSubject("Subject");
        email.setText("Text");
        emailService.sendAsync(email);
    }

    @Override
    public User changeAccountTypeFor(Long id, ChangeAccountTypeForm form, User user) {
        Assert.notNull(form, "ChangeAccountTypeForm must not be null");
        Assert.notNull(user, "User must not be null");
        User toChange = getOrThrow(id);
        toChange.setAccountType(AccountType.fromString(form.getAccountType()).name());

        update(user, user.getId());
        return user;
    }

    @Override
    public void resetPassword(ResetPasswordForm form) {
        // Send reset password link
    }

}
