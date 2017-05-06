package io.github.likewhat.springbase.exception;


public class EmailAlreadyInUseException extends RuntimeException {

    public EmailAlreadyInUseException(String email) {
        super(String.format("Email [ %s ] already exist", email));
    }
}
