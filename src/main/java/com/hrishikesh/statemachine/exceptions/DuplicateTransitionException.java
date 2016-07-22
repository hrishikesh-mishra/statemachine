package com.hrishikesh.statemachine.exceptions;

/**
 * <p>
 *     Duplicate Transition
 * </p>
 * Created by hrishikesh.mishra
 */
public class DuplicateTransitionException extends Exception {

    public DuplicateTransitionException() {

    }

    public DuplicateTransitionException(String message) {
        super(message);
    }

    public DuplicateTransitionException(String message, Throwable cause) {
        super(message, cause);
    }
}
