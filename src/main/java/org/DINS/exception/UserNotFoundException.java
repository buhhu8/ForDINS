package org.DINS.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Integer userId) {
        super("User with such ID " + userId + " doesn't exist");
    }

}
