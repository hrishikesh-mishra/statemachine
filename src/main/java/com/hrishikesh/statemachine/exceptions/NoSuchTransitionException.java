package com.hrishikesh.statemachine.exceptions;

/**
 * Created by hrishikesh.mishra on 22/07/16.
 */
public class NoSuchTransitionException extends Exception {

    public NoSuchTransitionException() {

    }

    public NoSuchTransitionException(String message) {
        super(message);
    }

    public NoSuchTransitionException(String message, Throwable cause) {
        super(message, cause);
    }
}
