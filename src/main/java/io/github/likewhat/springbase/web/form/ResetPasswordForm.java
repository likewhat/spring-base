package io.github.likewhat.springbase.web.form;


import org.hibernate.validator.constraints.Email;

public class ResetPasswordForm {

    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
