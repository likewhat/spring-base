package io.github.likewhat.springbase.security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;


public class SecurityUtils {

    private static final PasswordEncoder encoder = new StandardPasswordEncoder();


    public static String getCurrentUser(Object principal) {
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    public static String encodePassword(String password) {
        return encoder.encode(password);
    }
}
