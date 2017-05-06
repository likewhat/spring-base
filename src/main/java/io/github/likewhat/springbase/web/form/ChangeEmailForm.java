package io.github.likewhat.springbase.web.form;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class ChangeEmailForm {

    @Email
    private String newEmail;

    @NotEmpty
    private String password;

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
