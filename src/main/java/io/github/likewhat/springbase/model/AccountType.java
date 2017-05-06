package io.github.likewhat.springbase.model;


public enum AccountType {

    User("ROLE_USER"),
    Administrator("ROLE_ADMIN");

    private String authority;

    AccountType(String authority) {
        this.authority = authority;
    }

    /**
     * Get account type from string value.
     *
     * @param val string used to determine account type.
     * @return Account type.
     */
    public static AccountType fromString(String val) {
        for (AccountType at : AccountType.values()) {
            if (at.name().equalsIgnoreCase(val)) {
                return at;
            }
        }
        throw new IllegalArgumentException("Cannot convert [ " + val + "] to account type!");
    }

    public String getAuthority() {
        return authority;
    }
}
