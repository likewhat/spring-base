package io.github.likewhat.springbase.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class User extends AbstractAuditingEntity<Long> {

    public static final String EMAIL = "email";


    @Column(length = 32)
    private String firstName;


    @Column(length = 32)
    private String lastName;

    /**
     * email
     */
    @Column(length = 64, nullable = false)
    private String email;

    /**
     * Password
     */
    @JsonIgnore
    @Column(length = 128, nullable = false)
    private String password;

    /**
     * Whether user is confirmed
     */
    @Column(nullable = false)
    private boolean confirmed = false;

    /**
     * User by default.
     */
    @Column(length = 32, nullable = false)
    private String accountType = AccountType.User.name();


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

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public String getAuthority() {
        return AccountType.fromString(accountType).getAuthority();
    }
}
