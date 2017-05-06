package io.github.likewhat.springbase.web.form;


import io.github.likewhat.springbase.model.Constants;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;


public class RegistrationForm {

    @NotBlank(message = Constants.REQUIRED_FIELD)
    @Pattern(regexp = "\\w+")
    @Length(max = 32)
    private String firstName;

    @NotBlank(message = Constants.REQUIRED_FIELD)
    @Length(max = 32)
    @Pattern(regexp = "\\w+")
    private String lastName;

    @Email
    @NotBlank(message = Constants.REQUIRED_FIELD)
    @Length(max = 32)
    private String email;

    @NotBlank(message = Constants.REQUIRED_FIELD)
    @Length(max = 16, min = 6, message = "")
    private String password;

    @NotBlank(message = Constants.REQUIRED_FIELD)
    @Length(max = 16, min = 6, message = "")
    private String password2;

    @NotBlank(message = Constants.REQUIRED_FIELD)
    private String accountType;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @AssertTrue(message = "Password must match.")
    public boolean comparePasswords() {
        return password != null && password.equals(password2);
    }
}
