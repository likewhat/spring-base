package io.github.likewhat.springbase.web.form;


import io.github.likewhat.springbase.model.AccountType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;


/**
 * Form used to change account type.
 */
public class ChangeAccountTypeForm {

    @NotEmpty
    private String accountType;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }


    @AssertTrue(message = "Invalid account type.")
    public boolean checkAccountType() {
        try {
            AccountType.fromString(accountType);
            return true;
        } catch (IllegalArgumentException e) {
            // ignore
        }
        return false;
    }
}
