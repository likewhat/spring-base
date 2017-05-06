package io.github.likewhat.springbase.exception;


public class UserNotExistException extends RuntimeException {

    public UserNotExistException(Long id) {
        super(String.format("User id=[ %s ] doesn't exist!", id));
    }
}
