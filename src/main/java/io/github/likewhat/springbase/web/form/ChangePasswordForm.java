package io.github.likewhat.springbase.web.form;


import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;

public class ChangePasswordForm {

    @NotEmpty
    private String password;

    @NotEmpty
    private String newPassword;

    @NotEmpty
    private String newPassword2;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

    @AssertTrue(message = "New passwords not match")
    public boolean compareNewPasswords() {
        return newPassword != null && newPassword.equals(newPassword2);
    }
}
