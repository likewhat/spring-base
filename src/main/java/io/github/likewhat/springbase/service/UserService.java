package io.github.likewhat.springbase.service;


import java.util.List;

import io.github.likewhat.springbase.exception.UserNotExistException;
import io.github.likewhat.springbase.model.User;
import io.github.likewhat.springbase.web.form.ChangeAccountTypeForm;
import io.github.likewhat.springbase.web.form.ChangeEmailForm;
import io.github.likewhat.springbase.web.form.ChangePasswordForm;
import io.github.likewhat.springbase.web.form.InviteUserForm;
import io.github.likewhat.springbase.web.form.RegistrationForm;
import io.github.likewhat.springbase.web.form.ResetPasswordForm;


public interface UserService extends BaseService<User, Long> {

    /**
     * Query user with given email.
     *
     * @param email email used to find user.
     * @return optional user
     */
    User findByEmail(String email);


    /**
     * Find user with given user id.
     *
     * @param id The id used to find user
     * @return The user which id is given id
     * @throws UserNotExistException if no user found
     */
    User getOrThrow(Long id);

    /**
     * Create a user and return the created user object.
     *
     * @param user user to create
     * @return create user
     */
    User register(RegistrationForm user);

    /**
     * Create a new user
     *
     * @param form    The user creation parameters
     * @param creator The id of creator
     * @return The created user
     */
    User createUser(RegistrationForm form, Long creator);

    /**
     * Delete user with given user id.
     *
     * @param id      id of user to delete
     * @param deleter operator
     */
    User delete(Long id, User deleter);

    /**
     * List all users
     * <p>
     * //     * @param pageable pagination parameters
     *
     * @return One page users
     */
    List<User> listUsers();

    /**
     * Change user email.
     *
     * @param form change email form
     * @param user user to change
     */
    void changeEmailFor(ChangeEmailForm form, User user);


    /**
     * Change user password
     *
     * @param form change password form
     * @param user user to change
     */
    void changePasswordFor(ChangePasswordForm form, User user);

    /**
     * Invite user
     *
     * @param form Form contains invitation parameters
     * @param user The current user
     */
    void inviteUser(InviteUserForm form, User user);

    /**
     * Change user account type.
     *
     * @param id   The id of user to change
     * @param form The new account type
     * @param user The current user
     * @return The user with account type changed
     */
    User changeAccountTypeFor(Long id, ChangeAccountTypeForm form, User user);

    /**
     * Send reset password link
     *
     * @param form The reset password parameters
     */
    void resetPassword(ResetPasswordForm form);
}
